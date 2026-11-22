package tests;
import models.*;

public class ModelTest {

    public static void main(String[] args) {

        // Testing Player
        Player player = new Player("Paavani");
        player.addScore(10);

        System.out.println("Player: " + player.getName());
        System.out.println("Score: " + player.getScore());

        // Testing MCQQuestion
        String[] options = {"Java", "Python", "C++", "HTML"};

        MCQQuestion question = new MCQQuestion(
            "Which language is used for Java applications?",
            5,
            options,
            0
        );

        System.out.println("Correct answer: " +
            question.checkAnswer("Java"));

        System.out.println("Wrong answer: " +
            question.checkAnswer("Python"));

        // Testing Quiz
        Quiz quiz = new Quiz(QuizCategory.JAVA);
        quiz.addQuestion(question);

        System.out.println("Quiz Category: " + quiz.getCategory());
        System.out.println("Total Questions: " +
            quiz.getQuestions().size());
    }
}