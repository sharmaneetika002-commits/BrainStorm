
package app;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import models.Player;
import models.Question;
import models.MCQQuestion;
import models.QuizCategory;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("      WELCOME TO BRAINSTORM");
        System.out.println("================================");

        List<Player> players = new ArrayList<>();

        System.out.print("Enter number of players: ");
        int numberOfPlayers = Integer.parseInt(sc.nextLine());

        for (int i = 1; i <= numberOfPlayers; i++) {

            System.out.print("Enter Player " + i + " name: ");
            String name = sc.nextLine();

            players.add(new Player(name));
        }

        List<Question> questions = new ArrayList<>();

        questions.add(new MCQQuestion(
                "Which language is platform independent?",
                10,
                new String[]{"C", "Java", "Python", "C++"},
                1
        ));

        questions.add(new MCQQuestion(
                "Which keyword is used to inherit a class in Java?",
                10,
                new String[]{"implements", "extends", "inherits", "super"},
                1
        ));

        questions.add(new MCQQuestion(
                "Which of these is not a Java primitive data type?",
                10,
                new String[]{"int", "float", "String", "char"},
                2
        ));

        questions.add(new MCQQuestion(
                "Which SQL command retrieves data?",
                10,
                new String[]{"INSERT", "UPDATE", "SELECT", "DELETE"},
                2
        ));

        questions.add(new MCQQuestion(
                "Which data structure follows FIFO?",
                10,
                new String[]{"Stack", "Queue", "Tree", "Graph"},
                1
        ));

        questions.add(new MCQQuestion(
                "Which keyword creates an object in Java?",
                10,
                new String[]{"class", "new", "this", "static"},
                1
        ));

        questions.add(new MCQQuestion(
                "Which symbol is used for single-line comments in Java?",
                10,
                new String[]{"//", "/*", "#", "<!--"},
                0
        ));

        questions.add(new MCQQuestion(
                "Which SQL clause filters grouped data?",
                10,
                new String[]{"WHERE", "ORDER BY", "HAVING", "SELECT"},
                2
        ));

        questions.add(new MCQQuestion(
                "Which collection does not allow duplicate elements?",
                10,
                new String[]{"ArrayList", "HashSet", "LinkedList", "Vector"},
                1
        ));

        questions.add(new MCQQuestion(
                "Which keyword is used to create a constant in Java?",
                10,
                new String[]{"static", "final", "const", "fixed"},
                1
        ));

        QuizEngine engine = new QuizEngine(players, questions, sc);

        engine.startQuiz();

        sc.close();
    }
}
