package data;
import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List; 
import models.Question;
import java.util.Set;



public class QuestionBank {   

    private HashMap<String, ArrayList<Question>> questionBank;
    public QuestionBank()
    {
        questionBank = new HashMap<>(); 
    }
    public void addQuestion(String category, Question question)
    {
        if(!questionBank.containsKey(category))
        {
            questionBank.put(category, new ArrayList<>());
        }

        questionBank.get(category).add(question);
    }

    public List<Question> getQuestionsByCategory(String category)
    {
        return questionBank.getOrDefault(category, new ArrayList<>()); //used .getOrDefault to avoid nullPointerException
    }

    public Set<String> getCategories()
    {
        return questionBank.keySet();
    }

}
