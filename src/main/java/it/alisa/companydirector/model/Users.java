package it.alisa.companydirector.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Users {
    public int userId;
    public String userName;
    public String userRoleRef;
    public String password;
    public String displayName;
    public String jobName;
    public double salary;
    public String birthDate;

    public Users(int userId, String user_name, String password, String role_name, String display_name
            , String jobName, double salary, String birthDate) {
        this.userId = userId;
        this.userName = user_name;
        this.password = password;
        this.userRoleRef = role_name;
        this.displayName = display_name;
        this.jobName = jobName;
        this.salary = salary;
        this.birthDate = birthDate;
    }

    public Users(Users users) {
        this.userId = users.userId;
        this.userName = users.userName;
        this.password = users.password;
        this.userRoleRef = users.userRoleRef;
        this.displayName = users.displayName;
        this.jobName = users.jobName;
        this.salary = users.salary;
        this.birthDate = users.birthDate;
    }
}
