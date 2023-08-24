package com.agrodev.wifarm.entity.Pojo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class SellCropRequest {

    private String requestId;
    private String cropName;
    private String cropCategory;
    private String cropLocation;
    private String LGA;
    private String cropId;
    private String sellerId;
    private int amountToSell;
}
