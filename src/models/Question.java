package models;
public abstract class Question {
    private String questionText; 
    private int marks; 

    public Question(String questionText, int marks)
    {
        this.questionText = questionText; 
        this.marks = marks; 
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
        this.questionText = questionText;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
    public abstract boolean checkAnswer(String answer);


}
