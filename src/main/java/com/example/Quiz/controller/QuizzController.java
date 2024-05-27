package com.example.Quiz.controller;

import com.example.Quiz.Model.Question;
import com.example.Quiz.Model.QuestionWrapper;
import com.example.Quiz.Model.Response;
import com.example.Quiz.service.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz/")
public class QuizzController {

    @Autowired
    QuizzService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuizz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(category.toLowerCase(),numQ,title);
    }

    @GetMapping("getQuiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id){
        return quizService.getQuiz(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submit(@PathVariable int id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id,responses);
    }
}
