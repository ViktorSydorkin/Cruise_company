<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${sessionScope.lang == 1}">
    <fmt:setLocale value="eng"/>
</c:if>
<c:if test="${sessionScope.lang == 2}">
    <fmt:setLocale value="ua"/>
</c:if>
<fmt:setBundle basename="translation"/>
<html>
<head>
    <meta charset="UTF-8">
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <link href="" rel="stylesheet">
    <style>
        <%@include file="css/form-style.css"%>
    </style>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <title><fmt:message key="Settings"/></title>
</head>
<body>
<script>
    <c:if test="${sessionScope.ok == 'false'}">
       ${sessionScope.remove("ok")}
    Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: '<fmt:message key="Your_data_wasn't_edited!"/>'
    })
    </c:if>
</script>
<nav class="sticky navbar navbar-expand-md navbar-light navbar-dark bg-dark">
    <div class="container">
        <a href="${pageContext.request.contextPath}/?clear=true" class="navbar-brand"><fmt:message key="Cruise_company"/></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="justify-content-end collapse navbar-collapse" id="navbarContent">
            <ul class="justify-content-center navbar-nav mr-auto mb-2">
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/?clear=true" class="nav-link"><fmt:message key="Home"/></a>
                </li>
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/applicationList" class="nav-link">
                        <fmt:message key="My_applications"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/settings" class="nav-link">
                        <fmt:message key="Settings"/>
                    </a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link" id="navbarDarkDropdownMenuLink1" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        <fmt:message key="Language"/>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink1">
                        <li>
                            <form action="${pageContext.request.contextPath}/?lang=1" method="post">
                                <input class="dropdown-item" type="submit" value="English">
                            </form>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/?lang=2" method="post">
                                <input class="dropdown-item" type="submit" value="Ukrainian">
                            </form>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/logout" class="nav-link">
                        <fmt:message key="Logout"/>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<main class="main-settings">
    <div class="circle"></div>
    <div class="register-form-container">
        <form action="${pageContext.request.contextPath}/settings" method="post">
            <h1 class="form-title">
                <fmt:message key="Settings"/>
            </h1>
            <div class="form-fields">
                <div class="form-field">
                    <input name="name" type="text" placeholder="<fmt:message key="Name"/>"
                           value="${sessionScope.name}" required
                           pattern="^([A-ZА-ЯЄІ])((['][A-Zа-яєі])?)([a-zа-яєі]+)([-][A-ZА-ЯІ]((['][A-Zа-яєі])?)[a-zа-яєі']+)?"
                           title="Example: John, O'Connor, Andrew-Wall">
                </div>
                <div class="form-field">
                    <input name="surname" type="text" placeholder="<fmt:message key="Surname"/>"
                           value="${sessionScope.surname}" required
                           pattern="^([A-ZА-ЯЄІ])((['][A-Zа-яєі])?)([a-zа-яєі]+)([-][A-ZА-ЯІ]((['][A-Zа-яєі])?)[a-zа-яєі']+)?"
                           title="Example: John, O'Connor, Andrew-Wall">
                </div>
                <div class="form-field">
                    <input name="password" type="password" placeholder="<fmt:message key="Password"/>" required
                           minlength="6" maxlength="45">
                </div>
            </div>
            <div class="form-buttons">
                <input type="submit" class="button" value="<fmt:message key="Change"/>">
            </div>
        </form>
    </div>
</main>
</body>
</html>
