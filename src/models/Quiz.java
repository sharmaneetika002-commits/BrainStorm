package models;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private QuizCategory category;
    private List<Question> questions;

    // Constructor
    public Quiz(QuizCategory category) {
        this.category = category;
        this.questions = new ArrayList<>();
    }

    // Getter for category
    public QuizCategory getCategory() {
        return category;
    }

    // Setter for category
    public void setCategory(QuizCategory category) {
        this.category = category;
    }

    // Add a question
    public void addQuestion(Question question) {
        questions.add(question);
    }

    // Retrieve all questions
    public List<Question> getQuestions() {
        return questions;
    }
}