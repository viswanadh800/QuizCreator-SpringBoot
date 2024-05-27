package com.example.Quiz.service;

import com.example.Quiz.Model.Question;
import com.example.Quiz.Model.QuestionWrapper;
import com.example.Quiz.Model.Quiz;
import com.example.Quiz.Model.Response;
import com.example.Quiz.questionDAO.QuestionDAO;
import com.example.Quiz.questionDAO.QuizzDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        for(Question q:questions){
            System.out.println(q);
        }
        System.out.println("Size of questions is "+questions.size());
        return new ResponseEntity<>("Successfully created Quiz", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
        Quiz quiz=quizDAO.findById(id).get();
        List<Question> questionsInQuiz=quiz.getQuestions();
        List<QuestionWrapper> questionsForUser=new ArrayList<>();
        for(int i=0;i<questionsInQuiz.size();i++){
            Question q=questionsInQuiz.get(i);
            questionsForUser.add(new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4()));
        }
        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        Quiz quiz=quizDAO.findById(id).get();
        List<Question> questions=quiz.getQuestions();
        int ans=0;
        int i=0;
        for(Response response:responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                ans++;
            i++;
        }
        return new ResponseEntity<>(ans,HttpStatus.OK);
    }
}
