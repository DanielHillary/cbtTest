package com.jacinthsolutions.cbt.entity.Pojo;

import com.jacinthsolutions.cbt.entity.Options;
import com.jacinthsolutions.cbt.entity.Questions;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
public class QuestionReg {
    private Questions questions;
    private List<Options> optionsList;
}
