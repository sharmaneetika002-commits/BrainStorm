package models;

public class MCQQuestion extends Question {
    private String[] options;
    private int correctAnswer;

    public MCQQuestion(String questionText, int marks, String[] options, int correctAnswer) {
        super(questionText, marks);
        this.options = options;
        this.correctAnswer = correctAnswer;

    }

    public String[] getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return options[correctAnswer];
    }

    @Override
public boolean checkAnswer(String answer) {
    return answer != null &&
           answer.equalsIgnoreCase(options[correctAnswer]);
}

}