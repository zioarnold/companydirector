package it.alisa.companydirector.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoles {
    public int roleId;
    public String roleName;

    public UserRoles(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    UserRoles(UserRoles userRoles) {
        this.roleId = userRoles.roleId;
        this.roleName = userRoles.roleName;
    }
}
