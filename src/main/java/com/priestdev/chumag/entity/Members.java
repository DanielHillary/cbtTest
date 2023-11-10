package com.priestdev.chumag.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@RequiredArgsConstructor
@Data
public class Members implements Comparable<Members>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private int age;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private Long cellId;
    private String cellName;
    private String cellAddress;
    private Long zoneId;
    private String zoneName;
    private LocalDate membershipDate;
    private LocalDate firstTimeVisit;
    private boolean hasCompletedDCA;
    private boolean hasCompletedMaturity;
    private boolean hasCompletedEncounter;
    private boolean hasCompletedDLI;
    private boolean hasACell;
    private boolean hasADepartment;

    @Override
    public int compareTo(Members o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Members members = (Members) o;
        return Objects.equals(firstName, members.firstName)
                && Objects.equals(lastName, members.lastName)
                && Objects.equals(address, members.address)
                && Objects.equals(phoneNumber, members.phoneNumber)
                && Objects.equals(firstTimeVisit, members.firstTimeVisit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, address, phoneNumber, firstTimeVisit);
    }
}
