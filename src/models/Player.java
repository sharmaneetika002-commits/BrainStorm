//message: implement a getName method in this class!!!

package models;

public class Player {

    private String name;
    private int score;

    // Default score constructor
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    // Parameterized constructor
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
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
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // Method to increase score
    public void addScore(int marks) {
        this.score += marks;
    }
}