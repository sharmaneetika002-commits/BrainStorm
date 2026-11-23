package models;

public class MCQQuestion extends Question {

    private String[] options;
    private int correctAnswer;

    public MCQQuestion(String questionText, int marks, String[] options, int correctAnswer) {

        super(questionText, marks);

        // Check whether options are null or empty
        if (options == null || options.length == 0) {
            throw new IllegalArgumentException("Options cannot be null or empty.");
        }

        // Check whether correct answer index is valid
        if (correctAnswer < 0 || correctAnswer >= options.length) {
            throw new IllegalArgumentException("Invalid correct answer index.");
        }

        // Check whether individual options are valid
        for (int i = 0; i < options.length; i++) {
            if (options[i] == null || options[i].trim().isEmpty()) {
                throw new IllegalArgumentException("Options cannot contain null or blank values.");
            }
        }

        this.options = options.clone();
        this.correctAnswer = correctAnswer;
    }

    public String[] getOptions() {
        return options.clone();
    }

    public String getCorrectAnswer() {
        return options[correctAnswer];
    }

    @Override
    public boolean checkAnswer(String answer) {

        if (answer == null || answer.trim().isEmpty()) {
            return false;
        }

        answer = answer.trim().toUpperCase();

        if (answer.length() != 1) {
            return false;
        }

        int selectedIndex = answer.charAt(0) - 'A';

        if (selectedIndex < 0 || selectedIndex >= options.length) {
            return false;
        }

        return selectedIndex == correctAnswer;
    }
}