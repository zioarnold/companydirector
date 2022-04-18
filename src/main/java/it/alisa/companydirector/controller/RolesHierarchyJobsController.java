package it.alisa.companydirector.controller;

import it.alisa.companydirector.model.Users;
import it.alisa.companydirector.service.DBConnectionOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@RestController
public class RolesHierarchyJobsController {
    @RequestMapping("allRoles")
    public ModelAndView allRoles() throws SQLException {
        return new ModelAndView("allRoles")
                .addObject("user_logged_in", Users.webDisplayName)
                .addObject("roles_list", DBConnectionOperation.getRoles());
    }

    @RequestMapping("allJobs")
    public ModelAndView allJobs() throws SQLException {
        return new ModelAndView("allJobs")
                .addObject("user_logged_in", Users.webDisplayName)
                .addObject("jobs_list", DBConnectionOperation.getJobs());
    }

    @RequestMapping("allHierarchy")
    public ModelAndView allHierarchy() throws SQLException {
        return new ModelAndView("allHierarchy")
                .addObject("user_logged_in", Users.webDisplayName)
                .addObject("hierarchy_list", DBConnectionOperation.getHierarchyMList());
    }
}
