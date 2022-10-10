<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <link href="css/index-style.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <style>
        <%@include file="css/form-style.css"%>
    </style>
    <title>Edit staff</title>
</head>
<body>
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
<main class="main-settings">
    <div class="circle"></div>
    <div class="register-form-container">
        <form action="${pageContext.request.contextPath}/admin/editStaff" method="post">
            <h1 class="form-title">
                Edit staff
            </h1>
            <div class="form-fields">
                <div class="form-field">
                    <input name="name" type="text" placeholder="Name" required value="${param.name}"
                           pattern="^([A-ZА-ЯЄІЇ])((['][A-Zа-яєії])?)([a-zа-яєії]+)([-][A-ZА-ЯІЇ]((['][A-Zа-яєії])?)[a-zа-яєії']+)?">
                </div>
                <div class="form-field">
                    <input name="surname" type="text"  placeholder="Surname" required value="${param.surname}"
                           pattern="^([A-ZА-ЯЄІЇ])((['][A-Zа-яєії])?)([a-zа-яєії]+)([-][A-ZА-ЯІЇ]((['][A-Zа-яєії])?)[a-zа-яєії']+)?">
                </div>
                <div class="form-field">
                    <input name="post" type="text" placeholder="Post" required value="${param.post}">
                </div>
                <div class="form-field">
                    <input name="liner" list="liners" placeholder="Liner" required value="${param.liner}">
                    <datalist id="liners">
                        <c:forEach var="liner" items="${requestScope.linerList}">
                        <option value="${liner.id}">
                            </c:forEach>
                    </datalist>
                </div>
            </div>
            <div class="form-buttons">
                <input type="submit" class="button" value="Edit">
            </div>
        </form>
    </div>
</main>
</body>
</html>
