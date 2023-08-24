package com.agrodev.wifarm.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
public class Land {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long landId;
    private String landState;
    private String LGA;
    private String town;
    private double landUnits;
    private double landSize;
    private Date purchaseDate;
    private Date leaseDate;
    private Date releaseDate;
    private Date sellDate;
    private String soilType;
    private String plantedCrops;
    private double landPrice;
    private double interest;
}
