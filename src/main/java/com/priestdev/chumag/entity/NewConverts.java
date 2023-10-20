package com.priestdev.chumag.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class NewConverts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate dateOfConversion;
    private String conversionMonth;
    private String area;
    private String zone;
    private String houseAddress;
    private String email;
}
