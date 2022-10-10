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

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/form-style.css">
    <style>
        <%@include file="css/form-style.css"%>
    </style>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <title><fmt:message key="Logging_in"/></title>
</head>

<body>
<script>
    <c:if test="${sessionScope.ok == 'false'}">
       ${sessionScope.remove("ok")}
    Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text:'<fmt:message key="Entered_login_or_password_is_invalid!"/>',
        footer: '<a href="${pageContext.request.contextPath}/registration"><fmt:message
                        key="Register_an_account"/>?</a>'
    })
    </c:if>
</script>
<nav class="sticky navbar navbar-expand-md navbar-light navbar-dark bg-dark">
    <div class="container">
        <a href="${pageContext.request.contextPath}/" class="navbar-brand"><fmt:message key="Cruise_company"/></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="justify-content-end collapse navbar-collapse" id="navbarContent">
            <ul class="justify-content-center navbar-nav mr-auto mb-2">
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/" class="nav-link"> <fmt:message key="Home"/></a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<main class="main-settings">
    <div class="circle"></div>
    <div class="register-form-container">
        <form action="${pageContext.request.contextPath}/login" method="post">
            <h1 class="form-title">
                <fmt:message key="Logging_in"/>
            </h1>
            <div class="form-fields">
                <div class="form-field">
                    <input name="email" type="email" placeholder="<fmt:message key="Email"/>" required
                           pattern="\w{3,}@([a-z]*\.[a-z]+){1}"
                           title="example@gmail.com">
                </div>
                <div class="form-field">
                    <input name="password" type="password" placeholder="<fmt:message key="Password"/>" required
                           minlength="6" maxlength="45">
                </div>
            </div>
            <div class="form-buttons">
                <input type="submit" class="button" value="<fmt:message key="Login"/>">
            </div>
            <div class="form-buttons">
                <a href="${pageContext.request.contextPath}/registration" class="button"><fmt:message
                        key="Register_an_account"/></a>
            </div>
        </form>
    </div>
</main>
<script src="//cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>

