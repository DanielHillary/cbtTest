package com.priestdev.chumag.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@RequiredArgsConstructor
@Data
public class ZonalLeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String age;
    private String phoneNumber;
    private LocalDate birthDay;
    private String email;
    private String zone;
    private String area;
    private boolean hasCompletedDCA;
    private boolean hasCompletedMaturity;
    private boolean hasCompletedEncounter;
    private boolean hasCompletedDLI;
}
