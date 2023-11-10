package com.priestdev.chumag.entity.Pojo;

import com.priestdev.chumag.entity.ZonalLeader;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ZoneReporting {

    private String id;
    private LocalDate sunday;
    private ZonalLeader zonalLeader;
    private int numberOfCells;
    private int totalYearlyTarget;
    private int totalMonthlyTarget;
    private int totalWeeklyTarget;
    private int totalYearlyFirstTimer;
    private int totalMonthlyFirstTimer;
    private int totalWeeklyFirstTimer;
    private int totalYearlySecondTimer;
    private int totalMonthlySecondTimer;
    private int totalWeeklySecondTimer;
    private int totalYearlyFirstAndSecondTimer;
    private int totalMonthlyFirstAndSecondTimer;
    private int totalWeeklyFirstAndSecondTimer;
    private List<CellReporting> cellLeaders;

}
