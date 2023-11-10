package com.priestdev.chumag.entity.Pojo;

import com.priestdev.chumag.entity.CellLeader;
import lombok.Data;

import java.util.List;

@Data
public class CellReporting {

    private String reportId;
    private CellLeader cellLeader;
    private int weeklyTarget;
    private int monthlyTarget;
    private int weeklyTotalFirstTimers;
    private int weeklyTotalSecondTimers;
    private int monthlyTotalFirstTimers;
    private int monthlyTotalSecondTimers;
    private int weeklyTotalFirstAndSecondTimers;
    private int monthlyTotalFirstAndSecondTimer;
    private int yearlyTotalFirstTimers;
    private int yearlyTotalSecondTimers;
    private int yearlyTotalFirstAndSecondTimers;
    private int yearlyTotalFirstAndSecondTimer;
    private int yearlyTarget;
    private int encounter;
    private int monthlyEncounter;
    private int completedDCA;
    private int monthlyCompletedDCA;
    private int completedMaturity;
    private int monthlyCompleteMaturity;
    private int completedDLI;
    private int monthlyCompletedDLI;
    private List<CellWeeklyReport> weeklyFandS;
    private List<CellMonthlyReport> monthlyFandS;
}
