package it.alisa.companydirector.service;

import it.alisa.companydirector.model.Roles;
import it.alisa.companydirector.model.UserRoles;
import it.alisa.companydirector.model.Users;
import it.alisa.companydirector.model.WebUsers;
import lombok.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DBConnectionOperation {
    private static String hostname;
    private static String port;
    private static String sid;
    private static String username;
    private static String password;
    private static List<Users> webUsersList = new ArrayList<>();
    private static List<UserRoles> userRolesList = new ArrayList<>();
    private static List<Roles> roles = new ArrayList<>();

    private static boolean authenticated = false;

    public void initDB(String hostname, String port, String sid, String username, String password) {
        DBConnectionOperation.hostname = hostname;
        DBConnectionOperation.port = port;
        DBConnectionOperation.sid = sid;
        DBConnectionOperation.username = username;
        DBConnectionOperation.password = password;
    }

    public static boolean login(String username, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(hostname + ":" + port + "/" + sid, DBConnectionOperation.username, DBConnectionOperation.password);
        if (connection == null) {
            return false;
        } else {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select user_name, user_pwd, display_name from web_users where user_name = '" + username + "' and user_pwd = MD5('" + password + "');");

            if (!resultSet.next()) {
                return false;
            } else {
                do {
                    WebUsers.name = resultSet.getString("USER_NAME");
                    WebUsers.password = resultSet.getString("USER_PWD");
                    WebUsers.displayName = resultSet.getString("DISPLAY_NAME");
                } while (resultSet.next());
            }
            authenticated = true;
            resultSet.close();
            statement.close();
            connection.close();
            return true;
        }
    }

    public static void logout() {
        if (authenticated) {
            authenticated = false;
        }
    }

    public static boolean isAuthenticated() {
        return authenticated;
    }

    public static List<Users> getAllUsers() throws SQLException {
        webUsersList.clear();
        Connection connection = DriverManager.getConnection(hostname + ":" + port + "/" + sid, DBConnectionOperation.username, DBConnectionOperation.password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select user_name, role_name, display_name from web_users, user_roles where web_users.user_role_ref = user_roles.id;");
        while (resultSet.next()) {
            Users users = new Users(resultSet.getString("USER_NAME"), "",
                    resultSet.getString("ROLE_NAME"), resultSet.getString("DISPLAY_NAME"));

            webUsersList.add(new Users(users));
        }
        resultSet.close();
        statement.close();
        connection.close();
        return webUsersList;
    }

    public static List<UserRoles> getUserRolesList() throws SQLException {
        userRolesList.clear();
        Connection connection = DriverManager.getConnection(hostname + ":" + port + "/" + sid, DBConnectionOperation.username, DBConnectionOperation.password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select id, role_name from user_roles where role_name not like 'ADMIN';");
        while (resultSet.next()) {
            UserRoles userRoles = new UserRoles(resultSet.getInt("ID"), resultSet.getString("ROLE_NAME"));
            userRolesList.add(userRoles);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return userRolesList;
    }

    public static boolean addUser(String name, String password, String displayName, int role) throws SQLException {
        Connection connection = DriverManager.getConnection(hostname + ":" + port + "/" + sid, DBConnectionOperation.username, DBConnectionOperation.password);
        Statement statement = connection.createStatement();
        statement.execute("insert into web_users (user_name, user_role_ref, user_pwd, display_name) VALUES ('" + name + "'," + role + ", md5('" + password + "'),'" + displayName + "');");
        statement.close();
        connection.close();
        return true;
    }

    public static List<Roles> getRoles() throws SQLException {
        roles.clear();
        Connection connection = DriverManager.getConnection(hostname + ":" + port + "/" + sid, DBConnectionOperation.username, DBConnectionOperation.password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user_roles;");
        while (resultSet.next()) {
            Roles role = new Roles(resultSet.getInt("ID"), resultSet.getString("ROLE_NAME"));
            roles.add(new Roles(role));
        }
        resultSet.close();
        statement.close();
        connection.close();
        return roles;
    }
}
