package com.priestdev.chumag.entity.Pojo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CellWeeklyReport {

    private Long cellId;
    private int firstTimers;
    private int secondTimers;
    private int encounter;
    private int completedDCA;
    private int completedMaturity;
    private int completedDLI;
    private int totalFirstAndSecondTimers;
    private LocalDate sunday;
}
