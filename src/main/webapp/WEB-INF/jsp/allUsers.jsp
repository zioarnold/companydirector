<%@ page import="it.alisa.companydirector.model.Users" %>
<%@ page import="java.util.List" %>
<%@ page import="it.alisa.companydirector.model.UserRoles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<UserRoles> userRolesList = (List<UserRoles>) request.getAttribute("user_roles_list");%>
<% List<Users> usersList = (List<Users>) request.getAttribute("all_users"); %>
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
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" rel="stylesheet"/>
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
                <a class="nav-link" href="${pageContext.request.contextPath}/index">?????????????? <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownUsers" role="button"
                   data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">????????????????????????</a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownUsers">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/allUsers">???????????? ??????????????????????????</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/allRoles">???????????? ??????????</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/allHierarchy">???????????? ??????????????</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/allJobs">???????????? ??????????????????????????</a>
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
    <c:when test="${empty all_users}">
        <div class="container text-center">
            <h2>?????????????????????????? ???? ????????????????????! ?????????????? -> <a href="${pageContext.request.contextPath}/addUserPage"><span class="fa fa-plus"></span></a>
                ?????????? ???????????????? ????????????????????????</h2>
        </div>
    </c:when>
    <c:otherwise>
        <div class="container text-center">
            <h2>???????????? ??????????????????????????, ?????????????? -> <a href="${pageContext.request.contextPath}/addUserPage"><span class="fa fa-plus"></span></a>
                ?????????? ???????????????? ????????????????????????</h2>
            <hr>
            <div class="table-responsive">
                <table class="table table-striped table-bordered table-sm" id="myTable">
                    <thead>
                    <tr>
                        <th hidden>????</th>
                        <th>
                            ??????????
                        </th>
                        <th>
                            ????????
                        </th>
                        <th>
                            ?????? ????????????????????????
                        </th>
                        <th hidden>???????? ????????????????</th>
                        <th hidden>??????????????????</th>
                        <th hidden>????</th>
                        <th>????????????????</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        for (Users users : usersList) {
                    %>
                    <tr>
                        <td class="u-userId" hidden><%=users.getUserId()%></td>
                        <td class="u-userName"><%=users.getUserName()%></td>
                        <td class="u-userRoleRef"><%=users.getUserRoleRef()%></td>
                        <td class="u-userDisplayName"><%=users.getDisplayName()%></td>
                        <td class="u-userBirthDate" hidden><%=users.getBirthDate()%></td>
                        <td class="u-userJobName" hidden><%=users.getJobName()%></td>
                        <td class="u-userSalary" hidden><%=users.getSalary()%></td>
                        <td>
                            <span data-toggle="modal" data-target="#con-close-modal-add" class="fa fa-search cssbuttongo"></span>
                            &nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/deleteUser?userId=<%=users.getUserId()%>"><span class="fa fa-trash"></span></a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </c:otherwise>
</c:choose>
<!-- Modal edit-->
<div class="modal" id="con-close-modal-add" tabindex="-1" role="dialog" aria-labelledby="addModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addModalLabel">????????????????/?????????????????? ????????????????????</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="${pageContext.request.contextPath}/saveUser" method="post">
                <div class="modal-body">
                    <label hidden>???? ????????????????????????
                        <input type="number" name="userId" class="form-control input-group input-group-sm u-userId"
                               readonly hidden/>
                    </label>
                    <label>??????????
                        <input type="text" name="userName"
                               class="form-control input-group input-group-sm u-userName" readonly required/>
                    </label>
                    <label>????????????/????????
                        <select name="role" class="form-control" id="role" required>
                            <% for (UserRoles userRoles : userRolesList) {%>
                            <option value="<%=userRoles.getRoleId()%>"><%=userRoles.getRoleName()%>
                            </option>
                            <%}%>
                        </select>
                    </label>
                    <label>?????? ????????????????????????
                        <input type="text" name="userDisplayName"
                               class="form-control input-group input-group-sm u-userDisplayName" required/>
                    </label>
                    <label>???????? ????????????????
                        <input type="date" name="userBirthDate"
                               class="form-control input-group input-group-sm u-userBirthDate" required/>
                    </label>
                    <label>????????????
                        <input type="text" name="userJobName"
                               class="form-control input-group input-group-sm u-userJobName" required/>
                    </label>
                    <label>????
                        <input type="text" name="userSalary"
                               class="form-control input-group input-group-sm u-userSalary" required/>
                    </label>
                    <%--                    <label>????????/????????????</label>--%>
                    <%--                    <input type="text" name="userRoleRef" class="form-control input-group input-group-sm u-userRoleRef"--%>
                    <%--                           disabled/>--%>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal" id="close-modal-add"
                            name="close-modal-add">????????????????
                    </button>
                    <input type="submit" class="btn btn-success btn-sm" value="OK"/>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function () {
        $('#con-close-modal-add').on('show.bs.modal', function (e) {
            var _button = $(e.relatedTarget);
            // console.log(_button, _button.parents("tr"));
            var _row = _button.parents("tr");
            var uUserId = _row.find(".u-userId").text().trim();
            var uUserName = _row.find(".u-userName").text().trim();
            var uUserRoleRef = _row.find(".u-userRoleRef").text().trim();
            var uUserDisplayName = _row.find(".u-userDisplayName").text().trim();
            var uUserBirthDate = _row.find(".u-userBirthDate").text().trim();
            var uUserJobName = _row.find(".u-userJobName").text().trim();
            var uUserSalary = _row.find(".u-userSalary").text().trim();
            // console.log(_invoiceAmt, _chequeAmt);
            $(this).find(".u-userId").val(uUserId);
            $(this).find(".u-userName").val(uUserName);
            $(this).find(".u-userRoleRef").val(uUserRoleRef);
            $(this).find(".u-userDisplayName").val(uUserDisplayName);
            $(this).find(".u-userBirthDate").val(uUserBirthDate);
            $(this).find(".u-userJobName").val(uUserJobName);
            $(this).find(".u-userSalary").val(uUserSalary);
        });
    });
</script>
<script>
    $(document).ready(function () {
        $('#myTable').dataTable({
            "oLanguage": {
                "oPaginate": {
                    "sPrevious": "<-",
                    "sNext": "->",
                    "sLast": "-->>"
                },
                "sZeroRecords": "?????????????? ???? ?????????????? :-(",
                "sInfoEmpty": "?????????????????????????? 0 ???? 0 ???? 0 ??????????????",
                "sInfoFiltered": "(?????????????? ???? _MAX_ ?????????? ??????????????)",
                "sInfo": "???????????????????????? ???? _START_ ???? _END_ ???? _TOTAL_ ??????????????",
                "sSearch": "??????????:",
                "sLoadingRecords": "???????????????? - ????????????????...",
                "sLengthMenu": '???????????????? <select>' +
                    '<option value="10">10</option>' +
                    '<option value="20">20</option>' +
                    '<option value="30">30</option>' +
                    '<option value="40">40</option>' +
                    '<option value="50">50</option>' +
                    '<option value="-1">??????</option>' +
                    '</select> ????????????/????'
            }
        });
    })
</script>
</body>
</html>
