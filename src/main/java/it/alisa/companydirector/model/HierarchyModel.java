package it.alisa.companydirector.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HierarchyModel {
    private int id;
    private int degreeId;
    private String name;

    public HierarchyModel(HierarchyModel hierarchyModel) {
        this.id = hierarchyModel.id;
        this.degreeId = hierarchyModel.degreeId;
        this.name = hierarchyModel.name;
    }

    public HierarchyModel(int id, int degreeId, String name) {
        this.id = id;
        this.degreeId=degreeId;
        this.name = name;
    }
}
