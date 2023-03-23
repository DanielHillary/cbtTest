package com.agrodev.wifarm.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@RequiredArgsConstructor
@Data
public class Tasks {
    @Id
    @GeneratedValue
    private Long id;
    private String taskDescription;
    private String taskCategory;
    private String cropCategory;
    private String cropName;
    private String adminName;
    private LocalDateTime notificationTime;
}
