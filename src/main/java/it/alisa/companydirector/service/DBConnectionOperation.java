package it.alisa.companydirector.service;

import it.alisa.companydirector.model.*;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static List<Jobs> jobsList = new ArrayList<>();
    private List<Hierarchy> hierarchyList = new ArrayList<>();

    private static boolean authenticated = false;

    Logger logger = LoggerFactory.getLogger(DBConnectionOperation.class);

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

    public static Object getJobs() throws SQLException {
        jobsList.clear();
        Connection connection = DriverManager.getConnection(hostname + ":" + port + "/" + sid, DBConnectionOperation.username, DBConnectionOperation.password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select job_name, id_hierarchy, hierarchy.name as hierarchy_name from position, hierarchy;");
        while (resultSet.next()) {
            Jobs jobs = new Jobs(resultSet.getString("JOB_NAME"),
                    resultSet.getInt("ID_HIERARCHY"),
                    resultSet.getString("HIERARCHY_NAME"));
            jobsList.add(new Jobs(jobs));
        }
        resultSet.close();
        statement.close();
        connection.close();
        return jobsList;
    }

    public static List<Users> getAllUsers() throws SQLException {
        webUsersList.clear();
        Connection connection = DriverManager.getConnection(hostname + ":" + port + "/" + sid, DBConnectionOperation.username, DBConnectionOperation.password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select web_users.id as user_id, user_name, role_name, display_name, job_name, cast(salary as decimal), birth_date from web_users, user_roles, position where web_users.user_role_ref = user_roles.id and web_users.position_ref = position.id;");
        while (resultSet.next()) {
            Users users = new Users(resultSet.getInt("USER_ID"), resultSet.getString("USER_NAME"), "",
                    resultSet.getString("ROLE_NAME"),
                    resultSet.getString("DISPLAY_NAME"),
                    resultSet.getString("JOB_NAME"),
                    resultSet.getDouble("SALARY"),
                    resultSet.getString("BIRTH_DATE"));

            webUsersList.add(new Users(users));
        }
        resultSet.close();
        statement.close();
        connection.close();
        return webUsersList;
    }

    public static boolean updateUser(String userId, String userName, String role, String userDisplayName, String userBirthDate, String userJobName, String userSalary) throws SQLException {
        Connection connection = DriverManager.getConnection(hostname + ":" + port + "/" + sid, DBConnectionOperation.username, DBConnectionOperation.password);
        Statement statement = connection.createStatement();
        statement.executeUpdate("update web_users set display_name = '" + userDisplayName + "', salary = '" + userSalary + "', birth_date = '" + userBirthDate + "', user_role_ref = " + role + "  where id = " + userId + ";");
        statement.close();
        connection.close();
        return true;
    }

    public static boolean deleteUser(int userId) throws SQLException {
        Connection connection = DriverManager.getConnection(hostname + ":" + port + "/" + sid, DBConnectionOperation.username, DBConnectionOperation.password);
        Statement statement = connection.createStatement();
        statement.executeUpdate("delete from web_users where id = '" + userId + "';");
        statement.close();
        connection.close();
        return true;
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

    public List<Hierarchy> getHierarchyList() throws SQLException {
        hierarchyList.clear();
        Connection connection = DriverManager.getConnection(hostname + ":" + port + "/" + sid, DBConnectionOperation.username, DBConnectionOperation.password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select d.name                                               as degree_name,\n" +
                "       h.name                                               as hierarchy_name,\n" +
                "       p.job_name                                           as job_name,\n" +
                "       array_to_string(array_agg(wu.display_name), ', ') as display_name\n" +
                "from hierarchy h\n" +
                "         left join degree d on h.id_degree = d.id\n" +
                "         left join position p on h.id = p.id_hierarchy\n" +
                "         inner join web_users wu on p.id = wu.position_ref\n" +
                "group by degree_name, hierarchy_name, job_name;");
        int index = 0;
        while (resultSet.next()) {
            if (index > 1) {
                if (resultSet.getString("DEGREE_NAME").equalsIgnoreCase("Департамент")) {
                    Hierarchy hierarchy = new Hierarchy(null,
                            resultSet.getString("HIERARCHY_NAME"),
                            resultSet.getString("JOB_NAME"),
                            resultSet.getString("DISPLAY_NAME"));
                    hierarchyList.add(new Hierarchy(hierarchy));
                }
            }
            Hierarchy hierarchy = new Hierarchy(resultSet.getString("DEGREE_NAME"),
                    resultSet.getString("HIERARCHY_NAME"),
                    resultSet.getString("JOB_NAME"),
                    resultSet.getString("DISPLAY_NAME"));

            hierarchyList.add(new Hierarchy(hierarchy));
            index++;
        }
        logger.info(hierarchyList.toString());
        resultSet.close();
        statement.close();
        connection.close();
        return hierarchyList;
    }
}
