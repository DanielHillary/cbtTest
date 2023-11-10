package com.priestdev.chumag.entity.Pojo;

import com.priestdev.chumag.entity.CellLeader;
import com.priestdev.chumag.entity.ZonalLeader;
import lombok.Data;

import java.util.List;

@Data
public class MonthlyReporting {

    private String id;
    private List<ZoneReporting> zonalReports;
}
