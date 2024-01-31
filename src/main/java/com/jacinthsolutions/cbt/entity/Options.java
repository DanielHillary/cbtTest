package com.jacinthsolutions.cbt.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "Quiz_Option")
public class Options {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String option;
    private String optionAlphabet;
    private boolean isSelected;
    private boolean isOriginal;
    private int position;
    private int questionId;

    public  Options(Options source){
        this.option = source.getOption();
        this.optionAlphabet = source.getOptionAlphabet();
        this.isSelected = source.isSelected();
        this.questionId = source.getQuestionId();
        this.position = source.getPosition();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Options options = (Options) o;
        return id == options.id && Objects.equals(option, options.option) && Objects.equals(optionAlphabet, options.optionAlphabet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, option, optionAlphabet);
    }
}
