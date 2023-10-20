package com.priestdev.chumag.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@RequiredArgsConstructor
@Data
public class FirstTimer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String address;
    private String area;
    private String zone;
    private String phoneNumber;
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
