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
        question.setCategory(question.getCategory().toLowerCase());
        questionDAO.save(question);
     //   System.out.println(question.get)
        return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
    }

    public boolean doesExists(int id){
        return questionDAO.existsById(id);
    }
    public String updateQuestion(Question newQuestion, int id){
        if(doesExists(id)) {
            questionDAO.save(newQuestion);
            return "success";
        }
        else{
            return "Question with id '"+id+"' does not exists to update.";
        }
    }

    public String deleteQuestion(int id) {
        if(doesExists(id)) {
            questionDAO.deleteById(id);
            return "success";
        }
        else{
            return "Question with id '"+id+"' does not exists to delete.";
        }
    }
}
