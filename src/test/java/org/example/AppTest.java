package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.example.dao.MemberDAOImplementation;
import org.example.dao.MemberDao;
import org.example.dao.QuestionDAOImplementation;
import org.example.dao.QuestionDao;
import org.example.entity.Member;
import org.example.entity.Question;
import org.example.util.DBConnectionUtil;
import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AppTest {

    MemberDao memberDAO = new MemberDAOImplementation();
    QuestionDao questionDAO = new QuestionDAOImplementation();
    static Connection conn = null;

    @BeforeAll
    static void setUp() {
        conn = DBConnectionUtil.getConnection();
        System.out.println("Database Connection Successful");
    }

    @BeforeEach
    void checkConnection() {
        assertNotNull(conn, "Database connection should not be null");
    }

    @Test
    void testAddAndVefifyQuestion() {
        Question que = new Question(10000, "What is 2+1", "4", "3", "10", "100", "b");

        boolean inserted = questionDAO.insertQuestion(que);
        assertTrue(inserted, "Question should be inserted successfully");

        List<Question> questions = questionDAO.getAllQuestion();

        boolean isAdded = false;
        for (Question q : questions){
            if(q.getQuestionId() == 10000) isAdded = true;
        }

        assertTrue(isAdded, "Inserted question with ID 10000 should exist in database");
    }

    @Test
    void testUpdateAndVerifyQuestion() {
        Question que = new Question(10001, "What is 2+1", "4", "3", "10", "100", "b");

        boolean inserted = questionDAO.insertQuestion(que);
        assertTrue(inserted, "Question should be inserted before update");

        que.setOptionC("11");

        boolean updated = questionDAO.updateQuestion(que);
        assertTrue(updated, "Question update should be successful");

        List<Question> questions = questionDAO.getAllQuestion();

        boolean isUpdated = false;
        for (Question q : questions){
            if(q.getOptionC().equals("11")) isUpdated = true;
        }

        assertTrue(isUpdated, "Question optionC should be updated to '11'");
    }

    @Test
    void testRegisterUser() {
        Member m = new Member(10000, "ms10000", "ms10000@gmail.com");
        boolean inserted = memberDAO.insertMember(m);
        assertTrue(inserted, "User should be registered successfully");
    }

    @Test
    void testGetAllQuestion(){
        List<Question> questions = questionDAO.getAllQuestion();
        assertNotNull(questions);
    }

    @AfterAll
    static void cleanUpAll() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database connection closed successfully");
            }
        } catch (SQLException e) {
            fail("Failed to close database connection: " + e.getMessage());
        }
    }
}