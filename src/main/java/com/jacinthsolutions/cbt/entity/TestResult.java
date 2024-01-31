package com.jacinthsolutions.cbt.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private int numOfQuestions;
    private double testScore;
    private int rightAnswers;
    private int wrongAnswers;
    private int totalQuestions;
    private Long testId;
    private String userId;
    private String subject;
    private String category;
    private LocalDateTime testDate;
}
