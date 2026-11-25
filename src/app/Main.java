
package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Player;
import models.Question;
import database.QuestionDAO;

public class Main {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("       WELCOME TO BRAINSTORM");
        System.out.println("================================");

        Scanner sc = new Scanner(System.in);

        // Player registration
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

            System.out.print("Enter Player " + (i + 1) + " name: ");
            String name = sc.nextLine();

            Player player = new Player(name);
            players.add(player);
        }

        // Subject selection
        System.out.println("\nSelect a subject:");
        System.out.println("1. Java");
        System.out.println("2. JavaScript");
        System.out.println("3. C Programming");
        System.out.println("4. DBMS");

        int choice = 0;

        while (choice < 1 || choice > 4) {

            System.out.print("Enter your choice (1-4): ");
            String input = sc.nextLine();

            try {
                choice = Integer.parseInt(input);

                if (choice < 1 || choice > 4) {
                    System.out.println(
                        "Please enter a number between 1 and 4."
                    );
                }

            } catch (NumberFormatException e) {
                System.out.println(
                    "Invalid input! Please enter a number, not text."
                );
            }
        }

        String category;

        switch (choice) {
            case 1:
                category = "Java";
                break;
            case 2:
                category = "JavaScript";
                break;
            case 3:
                category = "C Programming";
                break;
            case 4:
                category = "DBMS";
                break;
            default:
                sc.close();
                return;
        }

        // Difficulty selection
        System.out.println("\nSelect difficulty:");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");

        int difficultyChoice = 0;

        while (difficultyChoice < 1 || difficultyChoice > 3) {

            System.out.print("Enter your choice (1-3): ");
            String input = sc.nextLine();

            try {
                difficultyChoice = Integer.parseInt(input);

                if (difficultyChoice < 1 || difficultyChoice > 3) {
                    System.out.println(
                        "Please enter a number between 1 and 3."
                    );
                }

            } catch (NumberFormatException e) {
                System.out.println(
                    "Invalid input! Please enter a number."
                );
            }
        }

        String difficulty;

        switch (difficultyChoice) {
            case 1:
                difficulty = "Easy";
                break;
            case 2:
                difficulty = "Medium";
                break;
            case 3:
                difficulty = "Hard";
                break;
            default:
                difficulty = "Easy";
        }

        // Fetch questions from MySQL
        QuestionDAO dao = new QuestionDAO();

        System.out.println(
            "\nLoading " + difficulty + " " + category + " questions..."
        );

        List<Question> questions =
            dao.getQuestionsByCategoryAndDifficulty(
                category, difficulty
            );

        if (questions.isEmpty()) {
            System.out.println(
                "No questions available for this subject and difficulty!"
            );
            sc.close();
            return;
        }

        System.out.println(
            "Successfully loaded " + questions.size() + " questions!"
        );

        // Start the quiz
        QuizEngine game = new QuizEngine(players, questions, sc);

        game.startQuiz();

        sc.close();
    }
}
