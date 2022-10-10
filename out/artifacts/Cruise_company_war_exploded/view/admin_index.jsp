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
    <title>Applications</title>
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
            <th>Cruise</th>
            <th>User</th>
            <th>State</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="app" items="${requestScope.applicationList}">
            <tr>
                <td>${app.id}</td>
                <td>${app.cruise.id}</td>
                <td>${app.user.id}</td>
                <td>
                    <c:if test="${app.approved == 'NO' && app.paid == 'UNPAID' && app.closed == 'CLOSED' && app.ended == 'NO'}">
                        <a href="${pageContext.request.contextPath}/admin/validate?app_id=${app.id}" class="btn btn-warning">
                            Validate
                        </a>
                    </c:if>
                    <c:if test="${app.approved == 'YES' && app.paid == 'UNPAID' && app.closed == 'CLOSED' && app.ended == 'NO'}">
                        Waiting for being paid
                    </c:if>
                    <c:if test="${app.approved == 'YES' && app.paid == 'PAID' && app.closed == 'CLOSED' && app.ended == 'NO'}">
                        <form action="${pageContext.request.contextPath}/admin/open?app_id=${app.id}&cruise=${app.cruise.id}" method="post">
                            <input type="submit" value="Open" class="btn btn-success">
                        </form>
                    </c:if>
                    <c:if test="${app.approved == 'YES' && app.paid == 'PAID' && app.closed == 'OPENED' && app.ended == 'NO'}">
                        <form action="${pageContext.request.contextPath}/admin/end?app_id=${app.id}" method="post">
                            <input type="submit" value="End" class="btn btn-danger">
                        </form>
                    </c:if>
                    <c:if test="${app.ended == 'YES'}">
                        Ended
                    </c:if>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin/validate?v=false&app_id=${app.id}" method="post">
                        <input type="submit" value="Delete" class="btn btn-danger">
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
