<%@ page import="java.util.List" %>
<%@ page import="it.alisa.companydirector.model.Roles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Roles> rolesList = (List<Roles>) request.getAttribute("roles_list");%>
<html>
<head>
    <title>Company Directory</title>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <link rel="stylesheet" href="css/background.css"/>
    <link type="text/javascript" href="js/bootstrap.js"/>
    <link rel="stylesheet" href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css">
    <!-- Load an icon library to show a hamburger menu (bars) on small screens -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e0e0d1 !important;">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/index">
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
                <a class="nav-link" href="${pageContext.request.contextPath}/index">Главная <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownUsers" role="button"
                   data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">Пользователи</a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownUsers">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/allUsers">Список пользователей</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/allRoles">Список групп</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/allHierarchy">Список отделов</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/allJobs">Список специализаций</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link">|</a>
            </li>
            <li>
                <a class="navbar-brand"><%=(String) request.getAttribute("user_logged_in") %>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link">|</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
            </li>
        </ul>
    </div>
</nav>
<c:choose>
    <c:when test="${empty roles_list}">
<%--        <div class="container text-center">--%>
<%--            <h2>Групп не существует! Нажмите -> <a href="/addRolesPage"><span class="fa fa-plus"></span></a>--%>
<%--                Чтобы добавить группу</h2>--%>
<%--        </div>--%>
    </c:when>
    <c:otherwise>
        <div class="container text-center">
<%--            <h2>Список групп, нажмите -> <a href="/addRolesPage"><span class="fa fa-plus"></span></a>--%>
<%--                Чтобы добавить группу</h2>--%>
            <hr>
            <div class="table-responsive">
                <table class="table table-striped table-bordered table-sm" id="myTable">
                    <thead>
                    <tr>
                        <th>
                            ID
                        </th>
                        <th>
                            Имя группы/роли
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        for (Roles roles : rolesList) {
                    %>
                    <tr>
                        <td><%=roles.getRoleId()%>
                        </td>
                        <td><%=roles.getRoleName()%>
                                <%--                            |--%>
                                <%--                            <button type="button" class="btn btn-primary btn-sm" data-toggle="modal"--%>
                                <%--                                    data-target="#con-close-modal-disable-user">--%>
                                <%--                                Изменить--%>
                                <%--                            </button>--%>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </c:otherwise>
</c:choose>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
        integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
        integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
        crossorigin="anonymous"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function () {
        $('#myTable').dataTable({
            "oLanguage": {
                "oPaginate": {
                    "sPrevious": "<-",
                    "sNext": "->",
                    "sLast": "-->>"
                },
                "sZeroRecords": "Записей не найдено :-(",
                "sInfoEmpty": "Предоставлено 0 до 0 из 0 записей",
                "sInfoFiltered": "(найдено из _MAX_ общих записей)",
                "sInfo": "Предоставляю от _START_ до _END_ из _TOTAL_ записей",
                "sSearch": "Поиск:",
                "sLoadingRecords": "Ожидайте - загружаю...",
                "sLengthMenu": 'Показать <select>' +
                    '<option value="10">10</option>' +
                    '<option value="20">20</option>' +
                    '<option value="30">30</option>' +
                    '<option value="40">40</option>' +
                    '<option value="50">50</option>' +
                    '<option value="-1">Все</option>' +
                    '</select> записи/ей'
            }
        });
    })
</script>
</body>
</html>
