package com.priestdev.chumag.entity.Pojo;

import com.priestdev.chumag.entity.ZonalLeader;
import com.priestdev.chumag.entity.ZonalLeaderReport;
import lombok.Data;

import java.util.List;

@Data
public class FollowUpReport {

    private String leaderFirstName;
    private String leaderLastName;
    private int totalWeeklyCalls;
    private int totalWeeklyCoupons;
    private int totalMonthlyCalls;
    private int totalMonthlyCoupons;
    private List<ZonalLeaderReport> reportList;
}
