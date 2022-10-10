<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/form-style.css">
    <style>
        <%@include file="css/form-style.css"%>
    </style>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <title><fmt:message key="Registration"/></title>
</head>

<body>
<main>
    <script>
        <c:if test="${sessionScope.ok == 'false'}">
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: '<fmt:message key="This_account_already_exists!"/>!',
            footer: '<a href="${pageContext.request.contextPath}/registration">Register a new account?</a>'
        })
        </c:if>
    </script>
    <div class="circle"></div>
    <div class="register-form-container">
        <form action="${pageContext.request.contextPath}/registration" method="post">
            <h1 class="form-title">
                <fmt:message key="Registration"/>
            </h1>
            <div class="form-fields">
                <div class="form-field">
                    <input name="name" type="text" placeholder="<fmt:message key="Name"/>" required
                           pattern="^([A-ZА-ЯЄІЇ])((['][A-Zа-яєії])?)([a-zа-яєії]+)([-][A-ZА-ЯІЇ]((['][A-Zа-яєії])?)[a-zа-яєії']+)?"
                           title="<fmt:message key="Name_example"/>">
                </div>
                <div class="form-field">
                    <input name="surname" type="text" placeholder="<fmt:message key="Surname"/>" required
                           pattern="^([A-ZА-ЯЄІЇ])((['][A-Zа-яєії])?)([a-zа-яєії]+)([-][A-ZА-ЯІЇ]((['][A-Zа-яєії])?)[a-zа-яєії']+)?"
                           title="<fmt:message key="Name_example"/>">
                </div>
                <div class="form-field">
                    <input name="email" type="email" placeholder="<fmt:message key="Login"/>" required
                           pattern="\w{3,}@([a-z]+\.[a-z]+){1}"
                           title="example@gmail.com">
                </div>
                <div class="form-field">
                    <input name="password" type="password" placeholder="<fmt:message key="Password"/>" required
                           minlength="6" maxlength="45">
                </div>
            </div>
            <div class="form-buttons">
                <input type="submit" class="button" value="<fmt:message key="Register"/>">
                <%--<button class="button">Register</button>--%>
            </div>
        </form>
    </div>
</main>
</body>

</html>
