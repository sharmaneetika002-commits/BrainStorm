
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import models.MCQQuestion;
import models.Question;

public class QuestionDAO {

    public List<Question> getQuestionsByCategory(String category) {

        List<Question> questions = new ArrayList<>();

        String sql = "SELECT * FROM questions WHERE category = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, category);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                String questionText = rs.getString("question_text");

                String[] options = {
                    rs.getString("option_a"),
                    rs.getString("option_b"),
                    rs.getString("option_c"),
                    rs.getString("option_d")
                };

                String correctOption = rs.getString("correct_option");
                int correctAnswer = correctOption.charAt(0) - 'A';

                int marks = rs.getInt("marks");

                MCQQuestion question = new MCQQuestion(
                    questionText,
                    marks,
                    options,
                    correctAnswer
                );

                questions.add(question);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }

public List<Question> getQuestionsByCategoryAndDifficulty(
        String category, String difficulty) {

    List<Question> questions = new ArrayList<>();

    String sql = "SELECT * FROM questions WHERE category = ? AND difficulty = ?";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, category);
        statement.setString(2, difficulty);

        try (ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {

                String questionText = rs.getString("question_text");

                String[] options = {
                    rs.getString("option_a"),
                    rs.getString("option_b"),
                    rs.getString("option_c"),
                    rs.getString("option_d")
                };

                String correctOption = rs.getString("correct_option");
                int correctAnswer = correctOption.charAt(0) - 'A';

                int marks = rs.getInt("marks");

                MCQQuestion question = new MCQQuestion(
                    questionText,
                    marks,
                    options,
                    correctAnswer
                );

                questions.add(question);
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return questions;
}



}


