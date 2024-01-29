package com.jacinthsolutions.cbt.entity.Pojo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GeneralReport {

    private Long cellId;
    private int firstTimers;
    private int secondTimers;
    private int encounter;
    private int completedDCA;
    private int completedMaturity;
    private int completedDLI;
    private int totalFirstAndSecondTimers;
    private String month;
    private int monthInt;
}
