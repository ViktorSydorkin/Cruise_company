<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="css/index-style.css" rel="stylesheet">
    <style>
        <%@include file="css/loader.css"%>
    </style>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <title>Users</title>
</head>
<body>
<div class="loader_bg">
    <div class="loader"></div>
</div>
<nav class="sticky navbar navbar-expand-md navbar-light navbar-dark bg-dark">
    <div class="container">
        <a href="${pageContext.request.contextPath}/" class="navbar-brand">Cruise company</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="justify-content-end collapse navbar-collapse" id="navbarContent">
            <ul class="justify-content-center navbar-nav mr-auto mb-2">
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/admin/main" class="nav-link">
                        Main
                    </a>
                </li>
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/admin/userList" class="nav-link">
                        Users list
                    </a>
                </li>
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/admin/staffList" class="nav-link">
                        Staff list
                    </a>
                </li>
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/admin/linerList" class="nav-link">
                        Liners list
                    </a>
                </li>
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/" class="nav-link">
                        Cruise list
                    </a>
                </li>
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/admin/create_staff" class="nav-link">
                        Add new staff
                    </a>
                </li>
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/admin/create_liner" class="nav-link">
                        Add new liner
                    </a>
                </li>
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/admin/create_cruise" class="nav-link">
                        Add new cruise
                    </a>
                </li>
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/logout" class="nav-link">
                        Log out
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div>
    <div class="alert alert-primary" role="alert" style="text-align: center">
        <span style="margin-right: 120px">Total amount of user on the server:
            <c:out value="${requestScope.total}"/></span>
        <span style="margin-left: 120px">
            Amount of users currently online:
            <c:out value="${requestScope.current}"/></span>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Login</th>
            <th>Preferred language</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${requestScope.userList}">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.email}</td>
                <td>${user.language.language}</td>
                <td>
                    <a class="apply btn btn-info"
                       href="${pageContext.request.contextPath}/admin/editUser?user=${user.email}&name=${user.name}&surname=${user.email}&language=${user.language.id}">
                        Change
                    </a>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin/deleteUser?user=${user.id}" method="post">
                        <input type="submit" value="Delete" class="apply btn btn-danger">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script>
    setTimeout(function(){
        $('.loader_bg').fadeToggle();
    }, 1500);
</script>
</body>
</html>
