package it.alisa.companydirector.controller;

import it.alisa.companydirector.model.WebUsers;
import it.alisa.companydirector.service.DBConnectionOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@RestController
public class RolesController {
    @RequestMapping("allRoles")
    public ModelAndView allRoles() throws SQLException {
        return new ModelAndView("allRoles")
                .addObject("user_logged_in", WebUsers.displayName)
                .addObject("roles_list", DBConnectionOperation.getRoles());
    }
}
