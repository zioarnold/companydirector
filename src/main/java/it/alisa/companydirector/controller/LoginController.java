package it.alisa.companydirector.controller;

import it.alisa.companydirector.model.ErrorCDStrings;
import it.alisa.companydirector.model.Users;
import it.alisa.companydirector.service.DBConnectionOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@RestController
public class LoginController {
    @Autowired
    private Environment environment;
    DBConnectionOperation dbConnectionOperation = new DBConnectionOperation();

    @RequestMapping("/")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping("/logout")
    public ModelAndView logout() {
        DBConnectionOperation.logout();
        return new ModelAndView("/login");
    }

    @RequestMapping("/login")
    public ModelAndView loginPage(@RequestParam(required = false, name = "username") String username,
                                  @RequestParam(required = false, name = "password") String password) throws SQLException {
        initDB();
        if (DBConnectionOperation.login(username, password)) {
            return new ModelAndView("index").addObject("user_logged_in", Users.webDisplayName)
                    .addObject("hierarchy_list", dbConnectionOperation.getHierarchyList());
        } else {
            return new ModelAndView("error")
                    .addObject("errorMsg", ErrorCDStrings.E_9999_UNKNOWN_ERROR.getErrorMsg());
        }
    }

    @RequestMapping("/index")
    public ModelAndView index() throws SQLException {
        if (DBConnectionOperation.isAuthenticated()) {
            return new ModelAndView("index").addObject("user_logged_in", Users.webDisplayName)
                    .addObject("hierarchy_list", dbConnectionOperation.getHierarchyList());
        } else
            return new ModelAndView("error").addObject("errorMsg", ErrorCDStrings.E_0001_UNKNOWN_ERROR.getErrorMsg());
    }

    private void initDB() {
        dbConnectionOperation.initDB(
                environment.getProperty("db.hostname"),
                environment.getProperty("db.port"),
                environment.getProperty("db.sid"),
                environment.getProperty("db.username"),
                environment.getProperty("db.password")
        );
    }
}
