<%@ page import="it.alisa.companydirector.model.UserRoles" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<UserRoles> userRolesList = (List<UserRoles>) request.getAttribute("user_roles_list");%>
<html>
<head>
    <title>Company Directory</title>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <link rel="stylesheet" href="css/background.css"/>
    <!-- Load an icon library to show a hamburger menu (bars) on small screens -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e0e0d1 !important;">
    <a class="navbar-brand" href="/index">
        <img src='<c:url value="img/eni-logo.png"/>' alt="CompanyDirectory"
             style="height: 20%; width: 20%;"/>Company<span
            style="color: yellow">Directory</span>
    </a>&nbsp;&nbsp;&nbsp;
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="index">Главная <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownUsers" role="button"
                   data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">Пользователи</a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownUsers">
                    <a class="dropdown-item" href="/allUsers">Список</a>
                    <a class="dropdown-item" href="/searchUserOnLDAPPage">TODO</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/massiveUploadPage">TODO</a>
                    <a class="dropdown-item" href="/singleUploadPage">TODO</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link">|</a>
            </li>
            <li>
                <a class="navbar-brand"><%=(String) request.getAttribute("user_logged_in") %>
                </a>
            </li>
            <%--            <li class="nav-item">--%>
            <%--                <a class="nav-link">|</a>--%>
            <%--            </li>--%>
            <%--            <li class="nav-item">--%>
            <%--                <a class="nav-link" href="/logout">Logout</a>--%>
            <%--            </li>--%>
        </ul>
    </div>
</nav>
<div class="container text-center">
    <form action="/addUser" method="post">
        <div class="row">
            <div class="form-group col-md-6">
                <label for="name">Логин</label>
                <input class="form-control" type="text" name="name" id="name"
                       placeholder="логин пользователя" required>
            </div>
            <div class="form-group col-md-6">
                <label for="password">Пароль</label>
                <input class="form-control" type="text" name="password" id="password"
                       placeholder="пароль пользователя" required>
            </div>
            <div class="form-group col-md-6">
                <label for="displayName">Отображаемое имя</label>
                <input class="form-control" type="text" name="displayName" id="displayName"
                       placeholder="Отображаемое имя" required>
            </div>
            <div class="form-group col-md-6">
                <label for="role">Роль
                    <select name="role" class="form-control" id="role">
                        <% for (UserRoles userRoles : userRolesList) {%>
                        <option value="<%=userRoles.getRoleId()%>"><%=userRoles.getRoleName()%></option>
                        <%}%>
                    </select>
                </label>
            </div>
        </div>
        <input type="submit" value="Добавить" class="btn btn-success">
    </form>
</div>
</body>
</html>
