package com.example.Quiz.service;

import com.example.Quiz.Model.Question;
import com.example.Quiz.Model.Quiz;
import com.example.Quiz.questionDAO.QuestionDAO;
import com.example.Quiz.questionDAO.QuizzDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizzService {
    @Autowired
    QuizzDAO quizDAO;

    @Autowired
    QuestionDAO questionDAO;

    public ResponseEntity<String> createQuiz(String category, int numQuestions, String title){
        List<Question> questions=questionDAO.findQustionsByCategoryAndNum(category,numQuestions);
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDAO.save(quiz);
        return new ResponseEntity<>("Successfully created Quiz", HttpStatus.CREATED);
    }
}
