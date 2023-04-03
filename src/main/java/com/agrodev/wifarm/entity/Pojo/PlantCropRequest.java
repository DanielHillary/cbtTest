package com.agrodev.wifarm.entity.Pojo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class PlantCropRequest {

    private Long marketCropId;
    private double quantityPlanted;
}
