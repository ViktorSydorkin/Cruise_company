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
    <title>Liners</title>
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
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Liner's name</th>
            <th>Deck amount</th>
            <th>Capacity</th>
            <th>Staff</th>
            <th>Company's title</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="liner" items="${requestScope.linerList}">
            <tr>
                <td>${liner.id}</td>
                <td>${liner.name}</td>
                <td>${liner.deck_amount}</td>
                <td>${liner.capacity}</td>
                <td><a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseStaff${liner.id}" role="button"
                       aria-expanded="false" aria-controls="collapseExample">
                    Staff info
                </a>
                </td>
                <td>${liner.company.title}</td>
                <td>
                    <a class="apply btn btn-info"
                       href="${pageContext.request.contextPath}/admin/editLiner?liner=${liner.id}&name=${liner.name}&deck_amount=${liner.deck_amount}&capacity=${liner.capacity}&company=${liner.company.id}">
                        Change
                    </a>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin/deleteLiner?liner=${liner.id}" method="post">
                        <input type="submit" value="Delete" class="apply btn btn-danger">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:forEach var="liner" items="${requestScope.linerList}">
    <div class="collapse" id="collapseStaff${liner.id}">
        <div class="card card-body">
            <table class="table table-sm">
                <thead>
                <tr>
                    <th>Liner</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Post</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="staff" items="${requestScope.staffList}">
                    <c:if test="${staff.liner.id == liner.id}">
                        <tr>
                            <td>${liner.id}</td>
                            <td>${staff.name}</td>
                            <td>${staff.surname}</td>
                            <td>${staff.post}</td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    </c:forEach>
    <script>
        setTimeout(function () {
            $('.loader_bg').fadeToggle();
        }, 1500);
    </script>
</body>
</html>
