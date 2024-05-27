package com.example.Quiz.questionDAO;

import com.example.Quiz.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<Question,Integer> {

    List<Question> findByCategory(String category);

//    @Query(value="SELECT * FROM Question Where Question.category=:category ORDER BY RANDOM() LIMIT :numQuestions",nativeQuery = true)
//    List<Question> findQustionsByCategoryAndNum(String category, int numQuestions);

    @Query(value="SELECT * FROM Question WHERE category = ?1 ORDER BY RANDOM() LIMIT ?2", nativeQuery = true)
    List<Question> findQustionsByCategoryAndNum(String category, int numQuestions);

}

