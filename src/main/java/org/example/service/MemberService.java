package org.example.service;

import org.example.dao.MemberDAOImplementation;
import org.example.dao.MemberDao;
import org.example.dao.QuestionDAOImplementation;
import org.example.dao.QuestionDao;
import org.example.entity.Member;
import org.example.entity.Question;

import java.util.List;
import java.util.Scanner;

public class MemberService {

    Scanner sc = new Scanner(System.in);
    MemberDao memberDAO = new MemberDAOImplementation();
    QuestionDao questionDAO = new QuestionDAOImplementation();

    public void registerUser() {

        System.out.print("\nEnter User ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        Member m = new Member(id,name,email);
        boolean isRegistered = memberDAO.insertMember(m);
        System.out.println(isRegistered ? "Member Registered Successfully" : "Operation Unsuccessful");

    }

    public void attendExam() {

        List<Question> quiz = questionDAO.getAllQuestion();

        int mark = 0;

        System.out.println("Quiz Started: ");

        for (Question q : quiz){

            System.out.println("Question 1: " + q.getQuestionText());
            System.out.println("A. " + q.getOptionA());
            System.out.println("B. " + q.getOptionB());
            System.out.println("C. " + q.getOptionC());
            System.out.println("D. " + q.getOptionD());

            System.out.print("Enter correct option: ");
            String answer = sc.nextLine();
            System.out.println();

            if(answer.trim().equalsIgnoreCase(q.getCorrectAnswer().trim().toLowerCase())) mark++;
        }

        System.out.println("Exam Over, mark scored: " + mark + " / " + quiz.size());
    }
}
