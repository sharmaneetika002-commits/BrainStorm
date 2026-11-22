package models;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private QuizCategory category;
    private List<Question> questions;
    private static QuizCategory validateCategory(QuizCategory category) {
    if (category == null) {
        throw new IllegalArgumentException("Quiz category cannot be null.");
    }

    return category;
}

    // Constructor
    public Quiz(QuizCategory category) {
        this.category = validateCategory(category);
        this.questions = new ArrayList<>();
    }

    // Getter for category
    public QuizCategory getCategory() {
        return category;
    }

    // Setter for category
    public void setCategory(QuizCategory category) {
        this.category = validateCategory(category);
    }

    // Add a question
    public void addQuestion(Question question) {
    if (question == null) {
        throw new IllegalArgumentException("Question cannot be null.");
    }

    questions.add(question);
}

    // Retrieve all questions
   public List<Question> getQuestions() {
    return new ArrayList<>(questions);
}
}