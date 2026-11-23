package app;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Scanner;

import models.MCQQuestion;
import models.Player;
import models.Question;

import scoring.ScoreManager;
import scoring.Leaderboard;

public class QuizEngine {

    private List<Player> players;
    private List<Question> questions;

    private static final int QUESTIONS_PER_GAME = 10;

    private ScoreManager scores = new ScoreManager();
    private Leaderboard leaderboard = new Leaderboard();

    private List<Player> winners;

    private Scanner sc;

    public QuizEngine(List<Player> players, List<Question> questions, Scanner sc) {

        this.players = players;
        this.questions = questions;
        this.sc = sc;
    }

    public void startQuiz() {

        System.out.println("QuizVerse is starting!");

        List<Question> selectedQuestions = getRandomQuestions(questions);

        if (selectedQuestions.isEmpty()) {
            System.out.println("No questions available!");
            return;
        }

        for (Question selectedQuestion : selectedQuestions) {

            System.out.println("\n" + selectedQuestion.getQuestionText());

            MCQQuestion mcq = (MCQQuestion) selectedQuestion;

            String[] options = mcq.getOptions();

            // Display options
            for (int i = 0; i < options.length; i++) {
                System.out.println((char) ('A' + i) + ". " + options[i]);
            }

            Map<Player, String> answers = new LinkedHashMap<>();

            // Collect answers from all players
            for (Player player : players) {

                System.out.println("\n" + player.getName() + " is answering");

                System.out.print("Enter your answer: ");

                String answer = sc.nextLine().trim();

                while (answer.length() != 1 ||
                       answer.toUpperCase().charAt(0) < 'A' ||
                       answer.toUpperCase().charAt(0) >= 'A' + options.length) {

                    System.out.println("Invalid answer, please enter one of the options.");

                    answer = sc.nextLine().trim();
                }

                answers.put(player, answer.toUpperCase());
            }

            // Reveal correct answer
            String correctAnswer = mcq.getCorrectAnswer();

            System.out.println("\nCorrect answer: " + correctAnswer);

            // Process scores
            for (Map.Entry<Player, String> entry : answers.entrySet()) {

                Player currentPlayer = entry.getKey();

                String playerAnswer = entry.getValue();

                boolean isCorrect = scores.processAnswer(
                    currentPlayer,
                    selectedQuestion,
                    playerAnswer
                );

                if (isCorrect) {
                    System.out.println(currentPlayer.getName() + ": Correct! You earn 10 points!");
                } else {
                    System.out.println(currentPlayer.getName() + ": Incorrect! Better luck next time.");
                }

                System.out.println("Current score: " + scores.getScore(currentPlayer));
            }
        }

        // Display leaderboard
        System.out.println("\n" + leaderboard.displayLeaderboard(players));

        // Display winners
        winners = leaderboard.getWinners(players);

        System.out.println("\nWinner(s):");

        for (Player winner : winners) {
            System.out.println(winner.getName());
        }
    }

    private List<Question> getRandomQuestions(List<Question> allQuestions) {

        if (allQuestions == null || allQuestions.isEmpty()) {
            return new ArrayList<>();
        }

        ArrayList<Question> list = new ArrayList<>(allQuestions);

        Collections.shuffle(list);

        int count = Math.min(QUESTIONS_PER_GAME, list.size());

        return new ArrayList<>(list.subList(0, count));
    }
}