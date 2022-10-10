<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tdt" uri="/WEB-INF/todayDateTime.tld" %>
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
    <title>Cruise editing</title>
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
        <form action="${pageContext.request.contextPath}/admin/editCruise" method="post">
            <h1 class="form-title">
                Editing cruise
            </h1>
            <div class="form-fields">
                <div class="form-field">
                    <input name="cruiseEng" type="text" placeholder="English title" required
                           value="${requestScope.translationENG}" pattern="^[A-Z][a-z' ]{1,}">
                </div>
                <div class="form-field">
                    <input name="cruiseUa" type="text" placeholder="Ukrainian title" required
                           value="${requestScope.translationUA}" pattern="^[А-ЯЄІЇ][а-яєії' ]{1,}">
                </div>
                <div class="form-field">
                    <input name="start" type="datetime-local" placeholder="Start date"
                           required value="${param.start}">
                </div>
                <div class="form-field">
                    <input name="end" type="datetime-local" placeholder="End date"
                           required value="${param.end}">
                </div>
                <div class="form-field">
                    <input name="price" type="number" placeholder="Price" min="1" required
                           value="${param.price}">
                </div>
                <div class="form-field">
                    <input name="available" type="number" min="0" placeholder="Available" required
                           value="${param.available}">
                </div>
                <div class="form-field">
                    <input name="liner" list="liners" placeholder="Liner" required
                           value="${param.liner}">
                    <datalist id="liners">
                        <c:forEach var="liner" items="${requestScope.linerList}">
                        <option value="${liner.id}">
                            </c:forEach>
                    </datalist>
                </div>
                <div class="form-field">
                    <input name="route" list="routes" placeholder="Route" required
                           value="${param.route}">
                    <datalist id="routes">
                        <c:forEach var="route" items="${requestScope.routeList}">
                        <option value="${route.id}">
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
