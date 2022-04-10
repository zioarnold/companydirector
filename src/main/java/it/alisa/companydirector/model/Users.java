package it.alisa.companydirector.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Users {
    public String userName;
    public String userRoleRef;
    public String password;
    public String displayName;

    public Users(String user_name, String password, String role_name, String display_name) {
        this.userName = user_name;
        this.password = password;
        this.userRoleRef = role_name;
        this.displayName = display_name;
    }

    public Users(Users users) {
        this.userName = users.userName;
        this.password = users.password;
        this.userRoleRef = users.userRoleRef;
        this.displayName = users.displayName;
    }
}
