package it.alisa.companydirector.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Jobs {
    private String jobName;
    private int hierarchyId;
    private String hierarchyName;

    public Jobs(String jobName,int hierarchyId, String hierarchyName) {
        this.jobName = jobName;
        this.hierarchyId = hierarchyId;
        this.hierarchyName = hierarchyName;
    }

    public Jobs(Jobs jobs) {
        this.jobName = jobs.jobName;
        this.hierarchyId = jobs.hierarchyId;
        this.hierarchyName = jobs.hierarchyName;
    }
}
