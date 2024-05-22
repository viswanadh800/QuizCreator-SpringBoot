package com.example.Quiz.questionDAO;

import com.example.Quiz.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizzDAO extends JpaRepository<Quiz,Integer> {
}
