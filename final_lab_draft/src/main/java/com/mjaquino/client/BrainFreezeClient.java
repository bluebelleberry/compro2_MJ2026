package com.mjaquino.client;

import com.mjaquino.models.*;
import com.mjaquino.services.*;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BrainFreezeClient {
    // data fields 
    private String host;
    private int port;
    // constructor
    public BrainFreezeClient(String host, int port) {
        this.host = host;
        this.port = port;
    }
    // starts the clients quiz application
    public void start() {
        try {
            Socket socket = new Socket(host, port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Gson gson = new Gson();
            Scanner scanner = new Scanner(System.in);
            String serverStatus = reader.readLine();

            if(serverStatus.equals("CLOSED")) {
                System.out.println(reader.readLine());
                socket.close();
                return;
            }

            printLine();
            System.out.println("                   B R A I N F R E E Z E");
            printLine();

            System.out.println();
            System.out.print("Student ID: ");
            String studentId = scanner.nextLine();
            System.out.print("First Name: ");
            String firstName = scanner.nextLine();
            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();
            System.out.print("Quiz Password: ");
            String password = scanner.nextLine();
            // creates authentication request
            AuthenticationRequest request = new AuthenticationRequest(
                    studentId,
                    firstName,
                    lastName,
                    password
            );

            writer.println(gson.toJson(request));
            boolean authenticated = Boolean.parseBoolean(reader.readLine());
            String message = reader.readLine();
            System.out.println();
            System.out.println(message);

            if(!authenticated) {
                System.out.println("   Possible reasons:\n" + " - Wrong password\n" + " - Student ID already used\n" + " - Quiz session is no longer available\n" + " - Maximum students reached");
                socket.close();
                return;
            }

            String serverStartTime = reader.readLine();
            String clientTimeIn = reader.readLine();
            // quiz header
            String title = reader.readLine();
            int duration = Integer.parseInt(reader.readLine());
            int totalPoints = Integer.parseInt(reader.readLine());
            int passingPercentage = Integer.parseInt(reader.readLine());
            long remainingTime = Long.parseLong(reader.readLine());
            long quizEndTime = System.currentTimeMillis() + remainingTime;
            int questionCount = Integer.parseInt(reader.readLine());

            List<Question> questions = new ArrayList<>();
            QuestionService questionService = new QuestionService();

            for(int i = 0; i < questionCount; i++) {
                String type = reader.readLine();
                String questionJson = reader.readLine();
                Question question = questionService.parseQuestion(
                        gson,
                        type,
                        questionJson
                );

                if(question != null) {
                    questions.add(question);
                }
            }

            if(questions.isEmpty()) {
                System.out.println("No questions received from server.");
                socket.close();
                return;
            }

            printLine();
            System.out.println("QUIZ INFORMATION");
            printSmallLine();
            System.out.println("Quiz Title          : " + title);
            System.out.println("Duration            : " + duration + " minutes");
            System.out.println("Total Points        : " + totalPoints);
            System.out.println("Passing Percentage  : " + passingPercentage + "%");
            System.out.println("Server Started At   : " + serverStartTime);
            System.out.println("Your Time In        : " + clientTimeIn);
            printLine();

            List<Object> answers = new ArrayList<>();

            for(int i = 0; i < questions.size(); i++) {
                answers.add("");
            }

            int currentIndex = 0;
            boolean submitted = false;
            // main quiz navigation
            while(!submitted) {

                if(System.currentTimeMillis() >= quizEndTime) {
                    System.out.println();
                    System.out.println("Time is up. Auto-submitting your answers...");
                    submitted = true;
                    break;
                }

                long secondsLeft = (quizEndTime - System.currentTimeMillis()) / 1000;
                long minutesLeft = secondsLeft / 60;
                long remainingSeconds = secondsLeft % 60;
                // display current question 
                Question question = questions.get(currentIndex);
                System.out.println();
                printLine();
                System.out.println("Time Remaining      : " + minutesLeft + "m " + remainingSeconds + "s");
                printSmallLine();
                System.out.println("Question            : " + (currentIndex + 1) + " of " + questions.size());
                System.out.println("Question Type       : " + question.getType());
                System.out.println("Points              : " + question.getPoints());
                printSmallLine();
                question.displayQuestion();
                System.out.println();
                Object currentAnswer = answers.get(currentIndex);

                if(currentAnswer == null || currentAnswer.toString().isEmpty()) {
                    System.out.println("Current Answer      : [No Answer]");
                } else if(currentAnswer instanceof List) {

                    List<?> listAnswer = (List<?>) currentAnswer;

                    System.out.println(
                            "Current Answer      : "
                                    + String.join(", ",
                                    listAnswer.stream()
                                            .map(Object::toString)
                                            .toList())
                    );

                } else {
                    System.out.println("Current Answer      : " + currentAnswer);
                }

                System.out.println();

                if(question instanceof EnumerationQuestion) {

                    System.out.println("Instruction         : Separate your answers with commas.");
                    System.out.print("Answer              : ");

                    String line = scanner.nextLine();

                    List<String> enumerationAnswers = Arrays.stream(line.split(","))
                            .map(String::trim)
                            .toList();

                    answers.set(currentIndex, enumerationAnswers);

                } else if(question instanceof MultipleChoiceQuestion) {

                    System.out.println("Instruction         : Choose the best answer.");
                    System.out.print("Answer              : ");

                    String answer = scanner.nextLine().trim().toUpperCase();

                    answers.set(currentIndex, answer);

                } else if(question instanceof WordBankQuestion) {

                    System.out.println("Instruction         : Type the correct word from the word bank.");
                    System.out.print("Answer              : ");

                    String answer = scanner.nextLine();

                    answers.set(currentIndex, answer);

                } else {

                    System.out.println("Instruction         : Type your answer clearly, wrong spelling is wrong.");
                    System.out.print("Answer              : ");

                    String answer = scanner.nextLine();

                    answers.set(currentIndex, answer);
                }

                System.out.println();

                if(System.currentTimeMillis() >= quizEndTime) {
                    System.out.println();
                    System.out.println("Time is up. Auto-submitting your answers...");
                    submitted = true;
                    break;
                }

                if(currentIndex == 0 && questions.size() > 1) {
                    System.out.print("[N] Next            : ");
                } else if(currentIndex == questions.size() - 1) {
                    System.out.print("[B] Back | [S] Submit: ");
                } else {
                    System.out.print("[B] Back | [N] Next : ");
                }

                String action = scanner.nextLine().trim().toUpperCase();

                if(action.equals("B") && currentIndex > 0) {
                    currentIndex--;
                } else if(action.equals("N") && currentIndex < questions.size() - 1) {
                    currentIndex++;
                } else if(action.equals("S") && currentIndex == questions.size() - 1) {
                    submitted = true;
                } else {
                    System.out.println();
                    System.out.println("Invalid choice. Please try again.");
                }
            }
            // sends submitted answer to the server 
            writer.println(gson.toJson(answers));

            String resultJson = reader.readLine();

            if(resultJson.equals("TIME_UP")) {
                System.out.println();
                System.out.println("Time is up. The quiz will automatically submit.");
                socket.close();
                return;
            }
            // receive and display quiz result
            ExamResult result = gson.fromJson(resultJson, ExamResult.class);

            System.out.println();
            printLine();
            System.out.println("                     QUIZ RESULT");
            printLine();

            System.out.println(result);

            System.out.println();
            printLine();
            System.out.println("                    ANSWER REVIEW");
            printLine();
            // show questions and its correct answers
            for(AnswerReview review : result.getReviews()) {
                System.out.println("Question            : " + review.getQuestion());
                System.out.println("Your Answer         : " + formatAnswer(review.getSubmittedAnswer()));
                System.out.println("Correct Answer      : " + formatAnswer(review.getCorrectAnswer()));
                System.out.println("Score Earned        : " + review.getScoreEarned() + "/" + review.getPoints());
                printSmallLine();
            }
            socket.close();
        } catch(Exception e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
    // for cleaner display for answers
    private String formatAnswer(Object answer) {
        if(answer == null) {
            return "[No Answer]";
        }
        if(answer instanceof List) {
            List<?> listAnswer = (List<?>) answer;
            return String.join(", ",
                    listAnswer.stream()
                            .map(Object::toString)
                            .toList());
        }
        if(answer.toString().trim().isEmpty()) {
            return "[No Answer]";
        }
        return answer.toString();
    }
    // dividers 
    private void printLine() {
        System.out.println("============================================================");
    }

    private void printSmallLine() {
        System.out.println("------------------------------------------------------------");
    }
}