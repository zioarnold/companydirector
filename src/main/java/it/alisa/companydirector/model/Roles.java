package it.alisa.companydirector.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Roles {
    private int roleId;
    private String roleName;

    public Roles(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Roles(Roles roles) {
        this.roleId = roles.roleId;
        this.roleName = roles.roleName;
    }
}
