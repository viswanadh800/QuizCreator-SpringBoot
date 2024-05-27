package com.example.Quiz.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
//    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
//    @JoinTable(name="quiz_questions",
//    joinColumns = {@JoinColumn(name="quiz_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name="questions_id", referencedColumnName = "id")}
//    )
    @ManyToMany
    private List<Question> questions;

    public Quiz() {
    }

    public Quiz(int id, String title, List<Question> questions) {
        this.id = id;
        this.title = title;
        this.questions = questions;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
