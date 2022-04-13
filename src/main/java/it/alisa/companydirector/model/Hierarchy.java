package it.alisa.companydirector.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hierarchy {
    private String degreeName;
    private String hierarchyName;
    private String jobName;
    private String displayName;

    @Override
    public String toString() {
        return degreeName + " -> " + hierarchyName + " -> " + jobName + " -> " + displayName + "\n";
//        return "Hierarchy{" + degreeName + '\'' +
//                hierarchyName + '\'' +
//                jobName + '\'' + displayName + '\'' +
//                '}';
    }

    public Hierarchy(String degree_name, String hierarchy_name, String job_name, String display_name) {
        this.degreeName = degree_name;
        this.hierarchyName = hierarchy_name;
        this.jobName = job_name;
        this.displayName = display_name;
    }

    public Hierarchy(Hierarchy hierarchy) {
        this.degreeName = hierarchy.degreeName;
        this.hierarchyName = hierarchy.hierarchyName;
        this.jobName = hierarchy.jobName;
        this.displayName = hierarchy.displayName;
    }
}
