package models;
public abstract class Question {
    private String questionText; 
    private int marks; 
    private static String validateQuestionText(String questionText) {
    if (questionText == null || questionText.trim().isEmpty()) {
        throw new IllegalArgumentException("Question text cannot be empty.");
    }

    return questionText.trim();
}
private static int validateMarks(int marks) {
    if (marks < 0) {
        throw new IllegalArgumentException("Marks cannot be negative.");
    }

    return marks;
}

    public Question(String questionText, int marks)
    {
        this.questionText = validateQuestionText(questionText);
        this.marks = validateMarks(marks);
    }

    public String getQuestionText()
    {
        return questionText;
    }
    public int getMarks()
    {
        return marks; 
    }
    public void setQuestionText(String questionText) {
        this.questionText = validateQuestionText(questionText);
    }

    public void setMarks(int marks) {
        this.marks = validateMarks(marks);
    }
    public abstract boolean checkAnswer(String answer);


}
