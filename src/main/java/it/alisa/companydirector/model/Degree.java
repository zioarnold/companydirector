package it.alisa.companydirector.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Degree {
    private int degreeId;
    private String name;

    public Degree(Degree degree) {
        this.degreeId = degree.degreeId;
        this.name = degree.name;
    }

    public Degree(int degreeId, String name) {
        this.degreeId = degreeId;
        this.name = name;
    }
}
