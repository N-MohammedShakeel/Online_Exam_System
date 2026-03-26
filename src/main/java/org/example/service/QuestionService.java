package org.example.service;

import org.example.dao.QuestionDAOImplementation;
import org.example.dao.QuestionDao;
import org.example.entity.Question;

import java.util.List;
import java.util.Scanner;

public class QuestionService {

    Scanner sc = new Scanner(System.in);
    QuestionDao questionDAO = new QuestionDAOImplementation();

    public void addQuestion() {

        System.out.print("Enter Question ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the question: ");
        String question = sc.nextLine();
        System.out.print("Enter option A: ");
        String optionA = sc.nextLine();
        System.out.print("Enter option B: ");
        String optionB = sc.nextLine();
        System.out.print("Enter option C: ");
        String optionC = sc.nextLine();
        System.out.print("Enter option D: ");
        String optionD = sc.nextLine();
        System.out.print("Enter the correct option: ");
        String answer = sc.nextLine();

        Question q = new Question(id,question,optionA,optionB,optionC,optionD,answer);

        boolean isAdded = questionDAO.insertQuestion(q);
        System.out.println(isAdded ? "Question Added Successfully" : "Operation Unsuccessful");
    }

    public void updateQuestion() {

        System.out.print("Enter Question ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        List<Question> questions = questionDAO.getAllQuestion();
        Question q = null;

        for (Question ques : questions){
            if(ques.getQuestionId() == id) q = ques;
        }

        if(q != null){
            System.out.print("Enter the question (Enter to skip): ");
            String question = sc.nextLine();
            if(!question.isEmpty()) q.setQuestionText(question);
            System.out.print("Enter option A (Enter to skip): ");
            String optionA = sc.nextLine();
            if(!optionA.isEmpty()) q.setOptionA(optionA);
            System.out.print("Enter option B (Enter to skip): ");
            String optionB = sc.nextLine();
            if(!optionB.isEmpty()) q.setOptionB(optionB);
            System.out.print("Enter option C (Enter to skip): ");
            String optionC = sc.nextLine();
            if(!optionC.isEmpty()) q.setOptionC(optionC);
            System.out.print("Enter option D (Enter to skip): ");
            String optionD = sc.nextLine();
            if(!optionD.isEmpty()) q.setOptionD(optionD);
            System.out.print("Enter the correct option (Enter to skip): ");
            String answer = sc.nextLine();
            if(!answer.isEmpty()) q.setCorrectAnswer(answer);

            boolean isUpdated = questionDAO.updateQuestion(q);
            System.out.println(isUpdated ? "Question Updated Successfully" : "Operation Unsuccessful");
        }else {
            System.out.println("Question Not Found.");
        }

    }

    public void displayQuestion() {

        List<Question> questions = questionDAO.getAllQuestion();

        if(!questions.isEmpty()){
            for (Question q : questions){
                System.out.println(q);
            }
        }else{
            System.out.println("No Questions to Display.");
        }
    }
}
