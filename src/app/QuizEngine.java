package app;

import java.util.List; 
import models.Player;
import models.Question; 
import java.util.ArrayList; 
import java.util.Collections; 
import java.util.Map; 
import java.util.HashMap; 
import java.util.Scanner;


public class QuizEngine 
{

    private List<Player> players;
    private List<Question> questions; 
    private static final int QUESTIONS_PER_GAME = 10; 
    private Scanner sc; 

    public QuizEngine(List<Player> players, List<Question> questions, Scanner sc)
    {
        this.players = players; 
        this.questions = questions; 
        this.sc = sc; 

    }

    public void startQuiz()
    {
        System.out.println("BrainStorm is starting!");
         List<Question> selectedQuestions = getRandomQuestions(questions);
        for(Question selectedQuestion: questions)

        {
           
            String visibleQuestion = selectedQuestion.getQuestionText();
            System.out.println(visibleQuestion);
            Map<Player,String> answers = new HashMap<>(); 


            for(Player player: players)
            {
                System.out.println(player.getName() + "is answering");
                System.out.print("Enter your answer(A/B/C/D): ");
                String answer = sc.nextLine();
                answers.put(player,answer);
            }
            
        }
    }

    private List<Question> getRandomQuestions(List<Question> allQuestions)
    {
            ArrayList<Question> list = new ArrayList<>(allQuestions);  //copy of the original list 
            Collections.shuffle(list); 
            int count = Math.min(QUESTIONS_PER_GAME, list.size()); //So that the program wont crash in case of questions less than 10; 
            List<Question> finalQuestions = list.subList(0,10);

            return finalQuestions; 
    }
}

    

