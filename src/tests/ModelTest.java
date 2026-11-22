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
        // Edge case testing
System.out.println("Lowercase Answer: " + q1.checkAnswer("java"));

System.out.println("Wrong Answer: " + q1.checkAnswer("Python"));

System.out.println("Null Answer: " + q1.checkAnswer(null));

        // Testing Quiz collection
        Quiz quiz = new Quiz(QuizCategory.JAVA);

        quiz.addQuestion(q1);
        quiz.addQuestion(q2);

        System.out.println("Category: " + quiz.getCategory());
        System.out.println("Total Questions: " +
            quiz.getQuestions().size());

        System.out.println("First question through Quiz: " +
            quiz.getQuestions().get(0).checkAnswer("Java"));
        // Testing invalid inputs

System.out.println("\n--- Validation Tests ---");

// MCQQuestion validation
try {
    new MCQQuestion("Test", 5, null, 0);
    System.out.println("Null options rejected: false");
} catch (IllegalArgumentException e) {
    System.out.println("Null options rejected: true");
}

try {
    new MCQQuestion("Test", 5, new String[]{}, 0);
    System.out.println("Empty options rejected: false");
} catch (IllegalArgumentException e) {
    System.out.println("Empty options rejected: true");
}

try {
    new MCQQuestion("Test", 5, new String[]{"A", "B"}, 5);
    System.out.println("Invalid index rejected: false");
} catch (IllegalArgumentException e) {
    System.out.println("Invalid index rejected: true");
}

// Player validation
try {
    new Player("   ");
    System.out.println("Blank player name rejected: false");
} catch (IllegalArgumentException e) {
    System.out.println("Blank player name rejected: true");
}

try {
    new Player("Test", -10);
    System.out.println("Negative score rejected: false");
} catch (IllegalArgumentException e) {
    System.out.println("Negative score rejected: true");
}

// Quiz validation
try {
    new Quiz(null);
    System.out.println("Null category rejected: false");
} catch (IllegalArgumentException e) {
    System.out.println("Null category rejected: true");
}

try {
    quiz.addQuestion(null);
    System.out.println("Null question rejected: false");
} catch (IllegalArgumentException e) {
    System.out.println("Null question rejected: true");
}   
System.out.println("\n--- Encapsulation Tests ---");
// Question validation

try {
    new MCQQuestion("   ", 5, new String[]{"A", "B"}, 0);
    System.out.println("Blank question rejected: false");
} catch (IllegalArgumentException e) {
    System.out.println("Blank question rejected: true");
}

try {
    new MCQQuestion("Test question", -5, new String[]{"A", "B"}, 0);
    System.out.println("Negative marks rejected: false");
} catch (IllegalArgumentException e) {
    System.out.println("Negative marks rejected: true");
}
// Additional validation tests

try {
    new Player(null);
    System.out.println("Null player name rejected: false");
} catch (IllegalArgumentException e) {
    System.out.println("Null player name rejected: true");
}

try {
    new MCQQuestion(null, 5, new String[]{"A", "B"}, 0);
    System.out.println("Null question text rejected: false");
} catch (IllegalArgumentException e) {
    System.out.println("Null question text rejected: true");
}

try {
    new MCQQuestion("Test", 5, new String[]{"A", " "}, 0);
    System.out.println("Blank MCQ option rejected: false");
} catch (IllegalArgumentException e) {
    System.out.println("Blank MCQ option rejected: true");
}

try {
    player1.setScore(-5);
    System.out.println("Negative setter score rejected: false");
} catch (IllegalArgumentException e) {
    System.out.println("Negative setter score rejected: true");
}

try {
    player1.addScore(-5);
    System.out.println("Negative score update rejected: false");
} catch (IllegalArgumentException e) {
    System.out.println("Negative score update rejected: true");
}

try {
    quiz.setCategory(null);
    System.out.println("Null category setter rejected: false");
} catch (IllegalArgumentException e) {
    System.out.println("Null category setter rejected: true");
}

// Testing setters
try {
    q1.setQuestionText("   ");
    System.out.println("Blank question setter rejected: false");
} catch (IllegalArgumentException e) {
    System.out.println("Blank question setter rejected: true");
}

try {
    q1.setMarks(-5);
    System.out.println("Negative marks setter rejected: false");
} catch (IllegalArgumentException e) {
    System.out.println("Negative marks setter rejected: true");
}


// Testing constructor defensive copy
String[] originalOptions = {"Red", "Blue"};

MCQQuestion testQuestion = new MCQQuestion(
    "Choose a color",
    5,
    originalOptions,
    0
);

originalOptions[0] = "Changed";

System.out.println("Constructor defensive copy: " +
    testQuestion.getCorrectAnswer().equals("Red"));

// Testing getter defensive copy
String[] retrievedOptions = testQuestion.getOptions();

retrievedOptions[0] = "Modified";

System.out.println("Getter defensive copy: " +
    testQuestion.getCorrectAnswer().equals("Red"));

// Testing Quiz collection protection
quiz.getQuestions().clear();

System.out.println("Quiz collection protected: " +
    (quiz.getQuestions().size() == 2));
    }
}