package com.priestdev.chumag.entity.Pojo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Data
public class Dashboard {

    private int totalMembers;
    private int totalMVP;
    private int totalSecondTimers;
    private int totalDCA;
    private int totalMaturity;
    private int totalDLI;
    private int totalEncounter;

}
