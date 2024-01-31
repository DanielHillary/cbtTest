package com.jacinthsolutions.cbt.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@RequiredArgsConstructor
public class CourseTest extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String course;
    private String courseCode;
    private String subject;
    private String testDescription;
    private int numOfTestQuestions;
    private int testDurationInHours;
    private int testDurationInMinutes;
    private String testCategory;
}
