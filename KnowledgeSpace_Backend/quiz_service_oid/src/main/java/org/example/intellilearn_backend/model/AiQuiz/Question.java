package org.example.intellilearn_backend.model.AiQuiz;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Table(name = "question")
@Entity
@Setter
@Getter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(columnDefinition = "TEXT")
    private String question;
    @Column(columnDefinition = "TEXT")
    private String option1;
    @Column(columnDefinition = "TEXT")
    private String option2;
    @Column(columnDefinition = "TEXT")
    private String option3;
    @Column(columnDefinition = "TEXT")
    private String option4;
    private int correctOptionNo;
    @Column(columnDefinition = "TEXT")
    private String solutionDescription;

    @ManyToOne
    private Quiz quiz;

}
