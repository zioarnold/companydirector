package it.alisa.companydirector.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Position {
    private int positionId;
    private int hierarchyId;
    private String jobName;
    public Position(int positionId, int hierarchyId, String jobName) {
        this.positionId = positionId;
        this.hierarchyId = hierarchyId;
        this.jobName = jobName;
    }

    public Position(Position position) {
        this.positionId = position.positionId;
        this.hierarchyId = position.hierarchyId;
        this.jobName = position.jobName;
    }
}
