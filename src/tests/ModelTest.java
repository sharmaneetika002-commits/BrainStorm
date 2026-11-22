package tests;

import models.*;

public class ModelTest {

    public static void main(String[] args) {

        // Testing multiple players
        Player player1 = new Player("Paavani");
        Player player2 = new Player("Player 2");

        player1.addScore(10);
        player1.addScore(5);
        player2.addScore(10);

        System.out.println("Player 1: " + player1.getName());
        System.out.println("Player 1 Score: " + player1.getScore());

        System.out.println("Player 2: " + player2.getName());
        System.out.println("Player 2 Score: " + player2.getScore());

        // Creating questions using inheritance
        String[] options1 = {"Java", "Python", "C++", "HTML"};

        Question q1 = new MCQQuestion(
            "Which is a programming language?",
            5,
            options1,
            0
        );

        String[] options2 = {
            "implements", "extends", "import", "package"
        };

        Question q2 = new MCQQuestion(
            "Which keyword is used for class inheritance in Java?",
            5,
            options2,
            1
        );

        // Testing polymorphism
        System.out.println("Q1 Correct: " + q1.checkAnswer("Java"));
        System.out.println("Q2 Correct: " + q2.checkAnswer("extends"));

        // Testing Quiz collection
        Quiz quiz = new Quiz(QuizCategory.JAVA);

        quiz.addQuestion(q1);
        quiz.addQuestion(q2);

        System.out.println("Category: " + quiz.getCategory());
        System.out.println("Total Questions: " +
            quiz.getQuestions().size());

        System.out.println("First question through Quiz: " +
            quiz.getQuestions().get(0).checkAnswer("Java"));
    }
}