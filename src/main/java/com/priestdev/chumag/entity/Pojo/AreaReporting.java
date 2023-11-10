package com.priestdev.chumag.entity.Pojo;

import com.priestdev.chumag.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class AreaReporting {

    private User areaLeader;
    private int numberOfCells;
    private List<CellReporting> cellReports;
}
