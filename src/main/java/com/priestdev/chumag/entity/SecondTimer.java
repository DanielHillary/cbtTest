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
public class SecondTimer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
    private LocalDate visitationDate;
    private String area;
    private Long cellId;
    private String cellName;
    private Long zoneId;
    private String zoneName;
    private String cellAddress;
    private String ageRange;
    private boolean forDCA;
    private boolean forShopForFree;
    private boolean forEncounter;
    private boolean forCareerSchool;
    private boolean forSportsAcademy;
    private boolean likedTheChurch;
    private String comments;
    private boolean willReturnAgain;
    private String mostEnjoyedSession;
    private String howDidYouHearAboutUs;
}
