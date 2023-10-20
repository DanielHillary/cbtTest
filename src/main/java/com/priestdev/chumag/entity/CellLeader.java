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
public class CellLeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String age;
    private LocalDate birthDay;
    private String email;
    private String zone;
    private String area;
    private String cellAddress;
    private boolean hasCompletedDCA;
    private boolean hasCompletedMaturity;
    private boolean hasCompletedEncounter;
    private boolean hasCompletedDLI;
    private boolean hasADepartment;
}
