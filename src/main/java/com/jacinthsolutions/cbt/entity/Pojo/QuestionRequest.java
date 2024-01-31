package com.jacinthsolutions.cbt.entity.Pojo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuestionRequest {

    private int testId;
    private String category;
    private String subject;
    private String userId;
}
