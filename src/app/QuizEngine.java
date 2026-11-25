
package app;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import models.Player;
import models.Question;
import models.MCQQuestion;
import ui.TimeService;

public class QuizEngine {

    private List<Player> players;
    private List<Question> questions;
    private static final int QUESTIONS_PER_GAME = 10;
    private Scanner sc;

    public QuizEngine(List<Player> players, List<Question> questions, Scanner sc) {
        this.players = players;
        this.questions = questions;
        this.sc = sc;
    }

    public void startQuiz() {

        System.out.println("\nWelcome to BrainStorm!");
        
        List<Question> selectedQuestions = getRandomQuestions(questions);

        for (Question selectedQuestion : selectedQuestions) {

            System.out.println("\n" + selectedQuestion.getQuestionText());

            if (selectedQuestion instanceof MCQQuestion) {
                String[] options = ((MCQQuestion) selectedQuestion).getOptions();

                for (int i = 0; i < options.length; i++) {
                    System.out.println((char)('A' + i) + ". " + options[i]);
                }
            }

            for (Player player : players) {

                System.out.println("\n" + player.getName() + " is answering");
                System.out.println("Enter your answer (A/B/C/D):");

                String answer = TimeService.getAnswerWithTimer(sc);

                if (answer == null) {
                    System.out.println(player.getName() + " did not answer in time!");
                    continue;
                }

                answer = answer.trim().toUpperCase();

                if (selectedQuestion instanceof MCQQuestion) {

                    MCQQuestion mcq = (MCQQuestion) selectedQuestion;
                    String[] options = mcq.getOptions();

                    if (answer.length() == 1) {

                        char choice = answer.charAt(0);
                        int index = choice - 'A';

                        if (index >= 0 && index < options.length) {

                            if (mcq.checkAnswer(options[index])) {
                                player.addScore(selectedQuestion.getMarks());
                                System.out.println("Correct answer! Points awarded: "
                                        + selectedQuestion.getMarks());
                            } else {
                                System.out.println("Wrong answer!");
                            }

                        } else {
                            System.out.println("Invalid answer!");
                        }

                    } else {
                        System.out.println("Invalid answer!");
                    }
                }
            }
        }

        System.out.println("\n===== FINAL LEADERBOARD =====");

        for (Player player : players) {
            System.out.println(player.getName() + " : " + player.getScore());
        }
    }

    private List<Question> getRandomQuestions(List<Question> allQuestions) {

        ArrayList<Question> list = new ArrayList<>(allQuestions);
        Collections.shuffle(list);

        int count = Math.min(QUESTIONS_PER_GAME, list.size());

        return new ArrayList<>(list.subList(0, count));
    }
}
