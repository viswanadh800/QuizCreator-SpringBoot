package com.example.Quiz.service;

import com.example.Quiz.Model.Question;
import com.example.Quiz.questionDAO.QuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDAO;
    public ResponseEntity<List<Question>> getAllQuestions(){
        try{
            return new ResponseEntity<>(questionDAO.findAll(), HttpStatus.OK);
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDAO.findByCategory(category);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDAO.save(question);
        return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
    }

    public boolean doesExists(int id){
        return true;
    }
    public String updateQuestion(Question question, int id){

        return "success";
    }

    public String deleteQuestion(int id) {
        questionDAO.deleteById(id);
        return "success";
    }
}
