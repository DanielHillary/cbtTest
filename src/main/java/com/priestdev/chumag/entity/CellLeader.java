package com.priestdev.chumag.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

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
    private Long zoneId;
    private String zoneName;
    private String area;
    private String cellAddress;
    private boolean hasCompletedDCA;
    private boolean hasCompletedMaturity;
    private boolean hasCompletedEncounter;
    private boolean hasCompletedDLI;
    private boolean hasADepartment;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "CELL_COVERAGE",
            joinColumns = {
                    @JoinColumn(name = "CELL_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "COVERAGE_ID")
            }
    )
    private Set<CoverageArea> coverageAreaSet;
}
