package com.agrodev.wifarm.entity.Pojo;

import com.agrodev.wifarm.entity.Crops;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
public class CropListRequest {

    private String reqId;
    private List<Crops> cropsList;
}
