//message: implement a getName method in this class!!!

package models;

public class Player {

    private String name;
    private int score;
    private static String validateName(String name) {
    if (name == null || name.trim().isEmpty()) {
        throw new IllegalArgumentException("Player name cannot be empty.");
    }

    return name.trim();
}
private static int validateScore(int score) {
    if (score < 0) {
        throw new IllegalArgumentException("Score cannot be negative.");
    }

    return score;
}

    // Default score constructor
    public Player(String name) {
        this.name = validateName(name);
        this.score = 0;
    }

    // Parameterized constructor
    public Player(String name, int score) {
        this.name = validateName(name);
        this.score = validateScore(score);
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    // Setters
    public void setName(String name) {
        this.name = validateName(name);
    }

    public void setScore(int score) {
        this.score = validateScore(score);
    }

    // Method to increase score
    public void addScore(int marks) {
    if (marks < 0) {
        throw new IllegalArgumentException("Marks cannot be negative.");
    }

    this.score += marks;
}
}