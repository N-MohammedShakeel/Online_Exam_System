package org.example.dao;

import org.example.entity.Question;
import org.example.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAOImplementation implements QuestionDao{


    @Override
    public List<Question> getAllQuestion() {
        String q = "Select * from question;";
        List<Question> list = new ArrayList<>();

        try(Connection con = DBConnectionUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();){

            while (rs.next()){
                Question ques = new Question(
                        rs.getInt("questionId"),
                        rs.getString("questionText"),
                        rs.getString("optionA"),
                        rs.getString("optionB"),
                        rs.getString("optionC"),
                        rs.getString("optionD")
                        ,rs.getString("correctAnswer"));
                list.add(ques);
            }

            return list;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public boolean insertQuestion(Question ques) {

        String q = "Insert into question (questionId,questionText,optionA,optionB,optionC,optionD,correctAnswer) values (?,?,?,?,?,?,?)";

        try(Connection con = DBConnectionUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(q);){

            ps.setInt(1,ques.getQuestionId());
            ps.setString(2,ques.getQuestionText());
            ps.setString(3,ques.getOptionA());
            ps.setString(4,ques.getOptionB());
            ps.setString(5,ques.getOptionC());
            ps.setString(6,ques.getOptionD());
            ps.setString(7,ques.getCorrectAnswer());

            return ps.executeUpdate() > 0;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean updateQuestion(Question ques) {
        String q = "Update question set questionText = ?, optionA = ?, optionB =?, optionC = ?, optionD = ?, correctAnswer = ? where questionId = ? ";

        try(Connection con = DBConnectionUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(q);){

            ps.setString(1,ques.getQuestionText());
            ps.setString(2,ques.getOptionA());
            ps.setString(3,ques.getOptionB());
            ps.setString(4,ques.getOptionC());
            ps.setString(5,ques.getOptionD());
            ps.setString(6,ques.getCorrectAnswer());
            ps.setInt(7,ques.getQuestionId());

            return ps.executeUpdate() > 0;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }
}
