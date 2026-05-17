package com.mjaquino.server;

import com.mjaquino.models.*;
import com.mjaquino.services.*;
import com.mjaquino.client.*;

import java.net.*;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

public class BrainFreezeServer {
        // data fields
        private int port;
        private String examFilePath;
        private boolean quizOpen;
        private long quizEndTime;
        private String serverStartTime;
        private Exam exam;
        private SessionService sessionService;
        private AuthenticationService authenticationService;
        private ResultService resultService;
        private QuestionService questionService;

        // constructor
        public BrainFreezeServer(int port, String examFilePath) {
                this.port = port;
                this.examFilePath = examFilePath;
                this.quizOpen = true;

                questionService = new QuestionService();
                exam = questionService.loadExam(examFilePath);
                sessionService = new SessionService();
                resultService = new ResultService();
                authenticationService = new AuthenticationService(sessionService, resultService);
        }
        // starts the server
        public void start() {
                if (exam == null) {
                        System.out.println("Failed to load exam.");
                        return;
                }

                quizEndTime = System.currentTimeMillis() + (exam.getDurationMinutes() * 60 * 1000);

                try {
                        serverStartTime = LocalTime.now().toString();
                        ServerSocket serverSocket = new ServerSocket(port);
                        serverSocket.setSoTimeout(1000);
                        printServerHeader();
                        startTimer();

                        while (isQuizOpen()) {
                                try {
                                        Socket clientSocket = serverSocket.accept();
                                        ClientHandler clientHandler = new ClientHandler( clientSocket, exam, authenticationService, sessionService, resultService, this);
                                        clientHandler.start();
                                }
                                catch (SocketTimeoutException e) {
                                }
                        }
                        serverSocket.close();
                        System.out.println();
                        printLine();
                        System.out.println("                 QUIZ SESSION CLOSED");
                        System.out.println("Closed At          : " + LocalTime.now());
                        printLine();
                        printQuizStatistics();
                        printLeaderboard();
                } catch (Exception e) {
                        System.out.println();
                        System.out.println("Server Error: " + e.getMessage());
                }
        }

        // checks if the quiz is still active
        public boolean isQuizOpen() {
                return quizOpen && System.currentTimeMillis() < quizEndTime;
        }
        // returns the remaining quiz time
        public long getRemainingTimeMillis() {
                return Math.max(0, quizEndTime - System.currentTimeMillis());
        }
        // get server start time 
        public String getServerStartTime() {
                return serverStartTime;
        }
        // start quiz countdown timer 
        private void startTimer() {
                Thread timerThread = new Thread(() -> {
                        // continuously update remaining time
                        while (System.currentTimeMillis() < quizEndTime) {
                                try {
                                        long secondsLeft = (quizEndTime - System.currentTimeMillis()) / 1000;
                                        long minutes = secondsLeft / 60;
                                        long seconds = secondsLeft % 60;
                                        System.out.print("\rQuiz Remaining Time : " + minutes + "m " + seconds + "s");
                                        Thread.sleep(1000);
                                } catch (Exception e) {
                                        System.out.println("Timer Error: " + e.getMessage());
                                }
                        }
                        quizOpen = false;
                        System.out.println();
                        printLine();
                        System.out.println("TIME IS UP. QUIZ IS NOW CLOSED.");
                        printLine();
                });
                timerThread.start();
        }
        // display quiz info
        private void printServerHeader() {
                printLine();
                System.out.println("                 BRAINFREEZE SERVER");
                printLine();
                System.out.println("Server Status       : ONLINE");
                System.out.println("Port                : "  + port);
                System.out.println("Server Started At   : " + serverStartTime);
                printSmallLine();
                System.out.println("Quiz Title          : " + exam.getTitle());
                System.out.println("Duration            : " + exam.getDurationMinutes() + " minutes");
                System.out.println("Total Questions     : " + exam.getQuestions().size());
                System.out.println("Total Points        : " + exam.getTotalPoints());
                System.out.println("Passing Percentage  : " + exam.getPassingPercentage() + "%");
                System.out.println("Maximum Students    : " + exam.getMaxStudents());
                printLine();
                System.out.println("Waiting for students to connect...");
                printLine();
        }
        // display student log in information
        public void printStudentLogin(
                Student student) {
                System.out.println();
                printSmallLine();
                System.out.println("STUDENT LOGIN DETECTED");
                printSmallLine();
                System.out.println("Student Name       : " + student.getFullName());
                System.out.println("Student ID         : " + student.getStudentId());
                System.out.println("Login Time         : " + LocalTime.now());
                System.out.println("Active Students    : " + sessionService.getActiveCount());
                printSmallLine();
        }
        // display submitted information
        public void printStudentSubmission(
                ExamResult result) {
                System.out.println();
                printSmallLine();
                System.out.println("QUIZ SUBMISSION RECEIVED");
                printSmallLine();
                System.out.println("Student Name       : " + result.getFullName());
                System.out.println("Student ID         : " + result.getStudentId());
                System.out.println("Score              : " + result.getScore() + "/" + result.getTotalPoints());
                System.out.println("Percentage         : " + String.format("%.2f",result.getPercentage()) + "%");
                System.out.println("Status             : " + result.getStatus());
                System.out.println("Ranking            : " + result.getRanking());
                printSmallLine();
        }
        // display quiz stats after the session ends
        private void printQuizStatistics() {
                List<ExamResult> results = resultService.loadResults();
                int passed = 0;
                int failed = 0;
                for (ExamResult result : results) {
                        if (result.getStatus().equalsIgnoreCase("PASSED")) {
                                passed++;
                        }
                        else {
                                failed++;
                        }
                }
                System.out.println();
                printLine();
                System.out.println("                 QUIZ STATISTICS");
                printLine();
                System.out.println("Total Participants : " + results.size());
                System.out.println("Passed Students    : " + passed);
                System.out.println("Failed Students    : " + failed);

                if (!results.isEmpty()) {
                        ExamResult topScorer = results.stream().max(Comparator.comparingInt(ExamResult::getScore)).orElse(null);
                        if (topScorer != null) {
                                System.out.println("Top Scorer         : " + topScorer.getFullName());
                                System.out.println("Highest Score      : " + topScorer.getScore() + "/" + topScorer.getTotalPoints());
                        }
                }
                printLine();
        }
        // display leaderboard ranking
        private void printLeaderboard() {
                List<ExamResult> results = resultService.loadResults();
                if (results.isEmpty()) {
                        System.out.println("No submitted results yet.");
                        return;
                }
                results.sort(Comparator.comparingInt(ExamResult::getScore).reversed());
                System.out.println();
                printLine();
                System.out.println("                    LEADERBOARD");
                printLine();
                int rank = 1;
                for (ExamResult result : results) {
                        System.out.println("RANK" + rank + ": " + result.getFullName());
                        System.out.println("     Student ID      : " + result.getStudentId());
                        System.out.println("     Score           : " + result.getScore() + "/" + result.getTotalPoints());
                        System.out.println("     Percentage      : " + String.format("%.2f", result.getPercentage()) + "%");
                        System.out.println("     Status          : " + result.getStatus());
                        printSmallLine();
                        rank++;
                }
        }
        // dividers
        private void printLine() {
                System.out.println("============================================================");
        }
        private void printSmallLine() {
                System.out.println("------------------------------------------------------------");
        }
}