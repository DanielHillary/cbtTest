package com.priestdev.chumag.entity.Pojo;

import com.priestdev.chumag.entity.FirstTimer;
import com.priestdev.chumag.entity.NewConverts;
import com.priestdev.chumag.entity.SecondTimer;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
public class CombinedMembers {

    private int id;
    private List<FirstTimer> allFirstTimers;
    private List<SecondTimer> allSecondTimers;
    private List<NewConverts> allNewConverts;
}
