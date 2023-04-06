package com.agrodev.wifarm.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class Crops {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cropId;
    private String cropCategory;
    private Long farmId;
    private String cropName;
    private String userId;
    private Integer cropEstimatedDurationInDays;
    private Integer cropActualDuration;
    private Date plantDate;
    private Date purchaseDate;
    private int amountPlanted;
    private boolean isPlanted;
    private boolean isHarvested;
    private Double minSquareMeter;
    private Double purchasePrice;
    private Date harvestDate;
    private double accruedAmount;
    private double principalAmount;
    private double monthlyInterestRate;
    private double estimatedYeildRate;

    public Crops (MarketCrops crops){
        this.cropName = crops.getCropName();
        this.cropCategory = crops.getCropCategory();
        this.cropEstimatedDurationInDays = crops.getCropEstimatedDuration();
        this.principalAmount = crops.getPrincipalAmount();
        this.accruedAmount = crops.getAccruedAmount();
        this.estimatedYeildRate = crops.getLifeCycleYieldRate();
        this.monthlyInterestRate = crops.getMonthlyInterestRate();
        this.minSquareMeter = crops.getSquareMeters();
        this.purchasePrice = crops.getCropPrice();
    }
}

