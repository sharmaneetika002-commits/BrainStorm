package database;

import java.util.List;
import models.Question;

public class TestQuestions {

    public static void main(String[] args) {

        QuestionDAO dao = new QuestionDAO();

        List<Question> questions =
            dao.getQuestionsByCategoryAndDifficulty("Java", "Easy");

        System.out.println("Questions fetched: " + questions.size());

        for (Question q : questions) {
            System.out.println(q.getQuestionText());
        }
    }
}