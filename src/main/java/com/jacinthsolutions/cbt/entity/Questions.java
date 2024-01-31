package com.jacinthsolutions.cbt.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
public class Questions extends Auditable<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String question;
    @Lob
    private String subject;
    private String category;
    private long testId;
    private String userId;
    private boolean isOriginal;
    private String usersChoice;
    private boolean answeredCorrectly;
    private String rightOption;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "QUESTION_OPTIONS",
            joinColumns = {
                    @JoinColumn(name = "QUEST_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "OPT_ID")
            }
    )
    private Set<Options> options;

    public Questions(Questions source) {
        this.question = source.getQuestion();
        this.subject = source.getSubject();
        this.category = source.getCategory();
        this.testId = source.getTestId();
        this.isOriginal = source.isOriginal();
        this.usersChoice = source.getUsersChoice();
        this.answeredCorrectly = source.isAnsweredCorrectly();
        this.rightOption = source.getRightOption();
        // Copy options (assuming Options implements equals and hashCode methods)
//        this.options = Set.copyOf(source.getOptions());
    }
}
