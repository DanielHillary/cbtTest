package com.agrodev.wifarm.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long farmId;
    private double landMass;
    private double squareMeters;
    private double dailyInterestRate;
    private double monthlyInterestRate;
    private boolean isLease;
    private boolean isOwned;
    private boolean isPlanted;
    private String customerId;
    private String farmLocation;
    private double principalAmount;
    private double accuredAmount;
    private double estimatedYeildRate;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "FARM_CROPS",
            joinColumns = {
                    @JoinColumn(name = "FARM_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "CROP_ID")
            }
    )
    private Set<Crops> cropsList;

}
