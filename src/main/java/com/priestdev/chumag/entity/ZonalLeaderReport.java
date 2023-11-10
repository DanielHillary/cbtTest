package com.priestdev.chumag.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
public class ZonalLeaderReport extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Long zoneId;
    private String zoneName;
    private LocalDate reportDate;
    private int reportMonth;
    private int coupons;
    private int calls;
}
