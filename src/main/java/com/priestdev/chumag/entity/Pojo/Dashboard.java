package com.priestdev.chumag.entity.Pojo;

import com.priestdev.chumag.entity.FirstTimer;
import com.priestdev.chumag.entity.Members;
import com.priestdev.chumag.entity.SecondTimer;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
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
    private List<FirstTimer> firstTimers;
    private List<SecondTimer> secondTimers;
    private List<Members> membersList;

}
