package com.priestdev.chumag.entity.Pojo;

import com.priestdev.chumag.entity.ZonalLeader;
import lombok.Data;

@Data
public class ZonalNewConvReport {

    private ZonalLeader leader;
    private Long zoneId;
    private String zoneName;
    private int newConverts;
    private int DCA;
    private int encounter;
    private int maturity;
    private int DLI;
    private int department;
    private int cell;
}
