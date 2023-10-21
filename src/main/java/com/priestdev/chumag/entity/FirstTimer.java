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
public class FirstTimer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String address;
    private String area;
    private Long zoneId;
    private String zoneName;
    private String gender;
    private LocalDate birthDay;
    private String phoneNumber;
    private String levelOfEducation;
    private String badComments;
    private String prayerRequest;
    private Long cellId;
    private String cellName;
    private String occupation;
    private String ageRange;
    private LocalDate firstVisitDate;
    private boolean forDCA;
    private boolean forShopForFree;
    private boolean forEncounter;
    private boolean forCareerSchool;
    private boolean forSportsAcademy;
    private boolean movieAcademy;
    private boolean centerForEducation;
    private boolean likedTheChurch;
    private boolean willReturnAgain;
    private String mostEnjoyedSession;
    private String howDidYouHearAboutUs;
    private String whoInvitedYou;
}
