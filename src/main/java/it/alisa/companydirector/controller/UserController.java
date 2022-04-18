package it.alisa.companydirector.controller;

import it.alisa.companydirector.model.ErrorCDStrings;
import it.alisa.companydirector.model.Users;
import it.alisa.companydirector.service.DBConnectionOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@RestController
public class UserController {
    @RequestMapping("/allUsers")
    public ModelAndView getAllUsers() throws SQLException {
        return new ModelAndView("allUsers")
                .addObject("user_logged_in", Users.webDisplayName)
                .addObject("all_users", DBConnectionOperation.getAllUsers())
                .addObject("user_roles_list", DBConnectionOperation.getUserRolesList());
    }

    @RequestMapping("/addUserPage")
    public ModelAndView addUserPage() throws SQLException {
        return new ModelAndView("addUserPage")
                .addObject("user_logged_in", Users.webDisplayName)
                .addObject("user_roles_list", DBConnectionOperation.getUserRolesList());
    }

    @RequestMapping("/addUser")
    public ModelAndView addUser(@RequestParam(name = "name") String name,
                                @RequestParam(name = "password") String password,
                                @RequestParam(name = "displayName") String displayName,
                                @RequestParam(name = "role") int role) throws SQLException {
        if (DBConnectionOperation.isAuthenticated()) {
            if (DBConnectionOperation.addUser(name, password, displayName, role)) {
                return new ModelAndView("success")
                        .addObject("user_logged_in", Users.webDisplayName)
                        .addObject("successMsg", "Пользователь успешно добавлен");
            } else return new ModelAndView("error")
                    .addObject("errorMsg", ErrorCDStrings.E_9999_UNKNOWN_ERROR.getErrorMsg());
        } else
            return new ModelAndView("error").addObject("errorMsg", ErrorCDStrings.E_0001_UNKNOWN_ERROR.getErrorMsg());
    }

    @RequestMapping("/deleteUser")
    public ModelAndView deleteUser(@RequestParam(name = "userId") int userId) throws SQLException {
        if (DBConnectionOperation.deleteUser(userId)) {
            return new ModelAndView("success")
                    .addObject("user_logged_in", Users.webDisplayName)
                    .addObject("successMsg", "Операция успешна,ы");
        } else return new ModelAndView("error")
                .addObject("errorMsg", ErrorCDStrings.E_9999_UNKNOWN_ERROR.getErrorMsg());
    }

    @RequestMapping("/saveUser")
    public ModelAndView saveUser(@RequestParam(name = "userId") String userId,
                                 @RequestParam(name = "userName") String userName,
                                 @RequestParam(name = "role") String role,
                                 @RequestParam(name = "userDisplayName") String userDisplayName,
                                 @RequestParam(name = "userBirthDate") String userBirthDate,
                                 @RequestParam(name = "userJobName") String userJobName,
                                 @RequestParam(name = "userSalary") String userSalary) throws SQLException {
        if (DBConnectionOperation.updateUser(userId, userName, role, userDisplayName, userBirthDate, userJobName, userSalary)) {
            return new ModelAndView("success")
                    .addObject("user_logged_in", Users.webDisplayName)
                    .addObject("successMsg", "Операция успешна,ы");
        } else return new ModelAndView("error")
                .addObject("errorMsg", ErrorCDStrings.E_9999_UNKNOWN_ERROR.getErrorMsg());
    }
}
