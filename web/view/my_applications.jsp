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
<html>
<head>
    <meta charset="UTF-8">
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="" rel="stylesheet">
    <style>
        <%@include file="css/loader.css"%>
    </style>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <title>My apps</title>
</head>
<body>
<script>
    <c:choose>
    <c:when test="${sessionScope.ok == 'app_sent'}">
       ${sessionScope.remove("ok")}
    Swal.fire({
        icon: 'success',
        title: 'Everything OK',
        text: '<fmt:message key="Your_application_is_waiting_for_validation!"/>',
    })
    </c:when>
    <c:when test="${sessionScope.ok == 'app_paid'}">
       ${sessionScope.remove("ok")}
    Swal.fire({
        icon: 'success',
        title: 'Everything OK',
        text: '<fmt:message key="Your_application_was_paid_successfully!"/>',
    })
    </c:when>
    <c:when test="${sessionScope.ok == 'not_sent'}">
       ${sessionScope.remove("ok")}
    Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: '<fmt:message key="Application_wasn't_applied!"/>!'
    })
    </c:when>
    <c:when test="${sessionScope.ok == 'not_paid'}">
       ${sessionScope.remove("ok")}
    Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: '<fmt:message key="Application_wasn't_paid!"/>'
    })
    </c:when>
    </c:choose>
</script>
<div class="loader_bg">
    <div class="loader"></div>
</div>
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
<div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="Application_id"/></th>
            <th><fmt:message key="Cruise"/></th>
            <th><fmt:message key="State"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="app" items="${requestScope.applicationList}">
            <tr>
                <td>${app.id}</td>
                <td>${app.cruise.cruiseTranslationList.get(0).title}</td>
                <c:if test="${app.approved == 'NO' && app.paid == 'UNPAID' && app.closed == 'CLOSED' && app.ended == 'NO'}">
                    <td><fmt:message key="In_queue"/></td>
                </c:if>
                <c:if test="${app.approved == 'YES' && app.paid == 'UNPAID' && app.closed == 'CLOSED' && app.ended == 'NO'}">
                    <td>
                        <form action="${pageContext.request.contextPath}/pay?app_id=${app.id}" method="post">
                            <input type="submit" class="btn btn-success" value="<fmt:message key="Pay"/>">
                        </form>
                    </td>
                </c:if>
                <c:if test="${app.approved == 'YES' && app.paid == 'PAID' && app.closed == 'CLOSED' && app.ended == 'NO'}">
                    <td>
                        <fmt:message key="Payment_check"/>
                    </td>
                </c:if>
                <c:if test="${app.approved == 'YES' && app.paid == 'PAID' && app.closed == 'OPENED' && app.ended == 'NO'}">
                    <td>
                        <fmt:message key="Opened"/>
                    </td>
                </c:if>
                <c:if test="${app.ended == 'YES'}">
                    <td>
                        <fmt:message key="Ended"/>
                    </td>
                </c:if>
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
