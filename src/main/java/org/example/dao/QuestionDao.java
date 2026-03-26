package org.example.dao;

import org.example.entity.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getAllQuestion();
    boolean insertQuestion(Question q);
    boolean updateQuestion(Question q);
}
