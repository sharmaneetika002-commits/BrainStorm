package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.MCQQuestion;
import models.Player;
import models.Question;
import data.QuestionBank;

public class Main {

    public static void main(String[] args) {
        QuestionBank bank = new QuestionBank();
        System.out.println("Welcome to BrainStorm");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of players: ");
        int numberOfPlayers = sc.nextInt();
        sc.nextLine();
        if (numberOfPlayers <= 0) {
            System.out.println("You need at least one player!");
            sc.close();
            return;
        }
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Enter your name: ");
            String name = sc.nextLine();
            Player player = new Player(name);
            players.add(player);

        }
        bank.addQuestion("Java", new MCQQuestion(
                "Which language runs on the JVM?",
                10,
                new String[] { "Python", "Java", "C", "JavaScript" },
                1));

        bank.addQuestion("Java", new MCQQuestion(
                "Which keyword is used to inherit a class in Java?",
                10,
                new String[] { "implements", "extends", "inherits", "super" },
                1));

        bank.addQuestion("DBMS", new MCQQuestion(
                "Which SQL command retrieves data?",
                10,
                new String[] { "INSERT", "UPDATE", "SELECT", "DELETE" },
                2));

        List<Question> questions = new ArrayList<>();

        questions.addAll(bank.getQuestionsByCategory("Java"));
        questions.addAll(bank.getQuestionsByCategory("DBMS"));
        QuizEngine game = new QuizEngine(players, questions, sc);
        game.startQuiz();

        sc.close();
    }
}
