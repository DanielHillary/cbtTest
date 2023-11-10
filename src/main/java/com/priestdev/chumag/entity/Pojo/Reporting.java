package com.priestdev.chumag.entity.Pojo;

import com.priestdev.chumag.entity.ZonalLeader;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Reporting {

    private String id;
    private LocalDate sunday;
    private int month;
    private ZonalLeader zonalLeader;
    private List<ZoneReporting> zoneReports;
}
