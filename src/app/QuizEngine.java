
package app;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import java.util.Scanner;

import models.Player;
import models.Question;
import models.MCQQuestion;

import scoring.ScoreManager;
import scoring.Leaderboard;

import ui.TimeService;

public class QuizEngine {

    private List<Player> players;
    private List<Question> questions;

    private static final int QUESTIONS_PER_GAME = 10;

    private ScoreManager scores = new ScoreManager();
    private Leaderboard leaderboard = new Leaderboard();

    private List<Player> winners;

    private Scanner sc;

    public QuizEngine(
            List<Player> players,
            List<Question> questions,
            Scanner sc) {

        this.players = players;
        this.questions = questions;
        this.sc = sc;
    }

    public void startQuiz() {

        System.out.println("\nWelcome to QuizVerse!");
        System.out.println("You have 10 seconds to answer each question.");

        List<Question> selectedQuestions =
                getRandomQuestions(questions);

        if (selectedQuestions.isEmpty()) {
            System.out.println("No questions available!");
            return;
        }

        // Go through each question
        for (Question selectedQuestion : selectedQuestions) {

            System.out.println("\n--------------------------------");
            System.out.println(selectedQuestion.getQuestionText());

            // Display MCQ options
            MCQQuestion mcq = (MCQQuestion) selectedQuestion;

            String[] options = mcq.getOptions();

            for (int i = 0; i < options.length; i++) {
                System.out.println(
                    (char) ('A' + i) + ". " + options[i]
                );
            }

            // Each player gets a turn
            for (Player player : players) {

                System.out.println(
                    "\n" + player.getName() + " is answering."
                );

                System.out.print("Enter your answer (A/B/C/D): ");

                // Get answer using multithreaded timer
                String answer = TimeService.getAnswerWithTimer(sc);

                // Handle timeout
                if (answer == null) {

                    System.out.println(
                        player.getName() + " did not answer in time!"
                    );

                    System.out.println("No points awarded.");
                    continue;
                }

                answer = answer.trim().toUpperCase();

                // Validate answer
                if (answer.length() != 1 ||
                    answer.charAt(0) < 'A' ||
                    answer.charAt(0) >= 'A' + options.length) {

                    System.out.println("Invalid answer!");
                    System.out.println("No points awarded.");
                    continue;
                }

                // Check answer and update score
                boolean isCorrect = scores.processAnswer(
                    player, selectedQuestion, answer
                );

                if (isCorrect) {

                    System.out.println(
                        "Correct answer! You earned 10 points!"
                    );

                } else {

                    System.out.println("Wrong answer!");
                }

                System.out.println(
                    "Current score: " + scores.getScore(player)
                );
            }
        }

        // Display final leaderboard
        System.out.println("\n===== FINAL LEADERBOARD =====");

        System.out.println(
            leaderboard.displayLeaderboard(players)
        );

        // Display winner
        winners = leaderboard.getWinners(players);

        System.out.println("\n===== WINNER(S) =====");

        for (Player winner : winners) {
            System.out.println(winner.getName());
        }
    }

    // Select up to 10 random questions
    private List<Question> getRandomQuestions(
            List<Question> allQuestions) {

        if (allQuestions == null || allQuestions.isEmpty()) {
            return new ArrayList<>();
        }

        ArrayList<Question> list =
                new ArrayList<>(allQuestions);

        Collections.shuffle(list);

        int count = Math.min(
            QUESTIONS_PER_GAME, list.size()
        );

        return new ArrayList<>(
            list.subList(0, count)
        );
    }
}
