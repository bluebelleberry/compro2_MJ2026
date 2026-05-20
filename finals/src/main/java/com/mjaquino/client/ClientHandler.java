package com.mjaquino.client;

import com.mjaquino.models.*;
import com.mjaquino.server.BrainFreezeServer;
import com.mjaquino.services.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ClientHandler extends Thread {
    // data fields
    private Socket socket;
    private Exam exam;
    private AuthenticationService authenticationService;
    private SessionService sessionService;
    private ResultService resultService;
    private BrainFreezeServer server;
    // constructor 
    public ClientHandler(
            Socket socket,
            Exam exam,
            AuthenticationService authenticationService,
            SessionService sessionService,
            ResultService resultService,
            BrainFreezeServer server
    ) {
        this.socket = socket;
        this.exam = exam;
        this.authenticationService = authenticationService;
        this.sessionService = sessionService;
        this.resultService = resultService;
        this.server = server;
    }

    // handles the entire client quiz session
    @Override
    public void run() {
        Student student = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Gson gson = new Gson();
            // prevents students from entering after quiz closes
            if(!server.isQuizOpen()) {
                writer.println("CLOSED");
                writer.println("Quiz is already closed.");
                socket.close();
                return;
            }
            writer.println("OPEN");
            String requestJson = reader.readLine();
            AuthenticationRequest request = gson.fromJson(requestJson, AuthenticationRequest.class);
            boolean authenticated = authenticationService.authenticate(request, exam);
            writer.println(authenticated);

            if(!authenticated) {
                writer.println("Authentication failed.\n" + "Possible reasons:\n" + "- Wrong password\n" + "- Student ID already used\n" + "- Quiz session is no longer available\n" + "- Maximum students reached");
                socket.close();
                return;
            }

            student = request.toStudent();
            server.printStudentLogin(student);
            String serverStartTime = server.getServerStartTime();
            String timeIn = LocalTime.now().toString();
            String quizDate = LocalDate.now().toString();

            writer.println("Authentication successful.");
            writer.println(serverStartTime);
            writer.println(timeIn);
            writer.println(exam.getTitle());
            writer.println(exam.getDurationMinutes());
            writer.println(exam.getTotalPoints());
            writer.println(exam.getPassingPercentage());
            writer.println(server.getRemainingTimeMillis());

            List<Question> preparedQuestions = new QuizService().prepareQuestions(exam);
            writer.println(preparedQuestions.size());

            for(Question question : preparedQuestions) {
                writer.println(question.getType().toString());
                writer.println(gson.toJson(question));
            }

            String answersJson = reader.readLine();
            Type answerListType = new TypeToken<List<Object>>() {}.getType();
            List<Object> answers = new ArrayList<>();

            if(answersJson != null && !answersJson.trim().isEmpty()) {
                answers = gson.fromJson(answersJson, answerListType);
            }
            if(answers == null) {
                answers = new ArrayList<>();
            }

            String timeOut = LocalTime.now().toString();

            int score = 0;
            int correctCount = 0;
            int incorrectCount = 0;

            List<AnswerReview> reviews = new ArrayList<>();
            // stores answer review info
            for(int i = 0; i < preparedQuestions.size(); i++) {
                Question question = preparedQuestions.get(i);
                Object submittedAnswer = "";
                if(i < answers.size()) {
                    submittedAnswer = answers.get(i);
                }
                int earned = question.getScore(submittedAnswer);
                score += earned;

                if(earned > 0) {
                    correctCount++;
                } else {
                    incorrectCount++;
                }

                reviews.add(new AnswerReview(question.getQuestion(),submittedAnswer,question.getCorrectAnswer(),earned,question.getPoints()));
            }
            // computes scores and answer reviews 
            int ranking = computeRanking(score);

            ExamResult result =
                    new ExamResult(
                            student.getStudentId(),
                            student.getFullName(),
                            timeIn,
                            timeOut,
                            quizDate,
                            score,
                            exam.getTotalPoints(),
                            correctCount,
                            incorrectCount,
                            ranking,
                            exam.getPassingPercentage(),
                            reviews
                    );

            resultService.saveResult(result);
            sessionService.submitSession(student.getStudentId());
            sessionService.removeSession(student.getStudentId());
            server.printStudentSubmission(result);
            writer.println(gson.toJson(result));
            socket.close();

        } catch(Exception e) {
            System.out.println();
            System.out.println("Client Handler Error: " + e.getMessage());
            if(student != null) {
                sessionService.removeSession(student.getStudentId());
            }
        }
    }
    // computes student leaderboard rank
    private int computeRanking(int currentScore) {
        List<ExamResult> results = resultService.loadResults();
        results.sort(Comparator.comparingInt(ExamResult::getScore).reversed());
        int rank = 1;
        for(ExamResult result : results) {
            if(currentScore < result.getScore()) {
                rank++;
            }
        }
        return rank;
    }
}