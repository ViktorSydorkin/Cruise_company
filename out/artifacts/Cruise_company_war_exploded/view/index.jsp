<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tdt" uri="/WEB-INF/todayDateTime.tld" %>
<%@ page import="java.lang.*" %>
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
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="css/index-style.css" rel="stylesheet">
    <style>
        <%@include file="css/index-style.css"%>
        <%@include file="css/loader.css"%>
    </style>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <title><fmt:message key="Cruise_company"/></title>
</head>
<body>
<script>
    <c:choose>
    <c:when test="${sessionScope.ok == 'true'}">
       ${sessionScope.remove("ok")}
    Swal.fire({
        icon: 'success',
        title: 'Everything OK',
        text: '<fmt:message key="You_have_successfully_registered_an_account!"/>!',
    })
    </c:when>
    <c:when test="${sessionScope.ok == 'edited'}">
       ${sessionScope.remove("ok")}
    Swal.fire({
        icon: 'success',
        title: 'Everything OK',
        text: '<fmt:message key="You_have_successfully_edited_your_data!"/>',
    })
    </c:when>
    <c:when test="${sessionScope.ok == 'nothing'}">
    ${sessionScope.remove("ok")}
    Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: '<fmt:message key="There_are_no_cruises_with_such_parameters!"/>',
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
                <c:if test="${sessionScope.name==null}">
                    <li class="nav-item">
                        <div class="container-fluid">
                            <form class="d-flex">
                                <input name="duration" class="form-control me-2" type="number" min="1" placeholder="<fmt:message key="Duration"/>"
                                       aria-label="Search">
                                <input name="date" class="form-control me-2" type="datetime-local" min="<tdt:TodayDateTime/>" placeholder="Start Date"
                                       aria-label="Search">
                                <button class="btn btn-outline-secondary" style="color: white; border-color: white" type="submit">Search</button>
                            </form>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown"
                           aria-expanded="false">
                            <fmt:message key="Language"/>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
                            <li><a href="${pageContext.request.contextPath}/?lang=1" class="dropdown-item">English</a>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/?lang=2" class="dropdown-item">Ukrainian</a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/login" class="nav-link">
                            <fmt:message key="Login"/>
                        </a>
                    </li>
                </c:if>

                <c:if test="${sessionScope.name!=null}">
                    <c:if test="${sessionScope.role == 'Admin'}">
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
                    </c:if>
                    <c:if test="${sessionScope.role.equals('User')}">
                        <li class="nav-item">
                            <div class="container-fluid">
                                <form class="d-flex">
                                    <input name="duration" class="form-control me-2" type="number" min="1" placeholder="Duration"
                                           aria-label="Search">
                                    <input name="date" class="form-control me-2" type="datetime-local" min="<tdt:TodayDateTime/>" placeholder="Start Date"
                                                                      aria-label="Search">
                                    <button class="btn btn-outline-secondary" style="color: white; border-color: white" type="submit">Search</button>
                                </form>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/?clear=true" class="nav-link"> <fmt:message key="Home"/></a>
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
                    </c:if>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/logout" class="nav-link">
                            <fmt:message key="Logout"/>
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
<div>
    <div class="cruise-container">
        <c:forEach items="${requestScope.cruiseList}" var="cruise">
            <div class="cruise-container-element border bg-light">
                <h5 class="card-title">${cruise.cruiseTranslationList.get(0).title}</h5>
                <table class="table">
                    <thead>
                    <tr>
                        <th><fmt:message key="Photo"/></th>
                        <th><fmt:message key="Cruise_startDate"/></th>
                        <th><fmt:message key="Cruise_endDate"/></th>
                        <th><fmt:message key="Price"/></th>
                        <th><fmt:message key="Port_amount"/></th>
                        <th><fmt:message key="Cruise_startRoute"/></th>
                        <th><fmt:message key="Cruise_endRoute"/></th>
                        <th><fmt:message key="Available"/></th>
                        <th><fmt:message key="Liner_name"/></th>
                        <th><fmt:message key="Liner_capacity"/></th>
                        <th><fmt:message key="Liner_deckAmount"/></th>
                        <th><fmt:message key="Liner_companyTitle"/></th>
                        <c:if test="${sessionScope.name!=null && sessionScope.role == 'User' && cruise.available > 0}">
                            <th><fmt:message key="Apply"/></th>
                        </c:if>
                        <c:if test="${sessionScope.name!=null && sessionScope.role == 'Admin'}">
                            <th><fmt:message key="Change"/></th>
                        </c:if>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <div class="cruise-description">
                            <td><a href="${pageContext.request.contextPath}/linerPhoto?liner=${cruise.liner.id}"
                                   target="_blank"><fmt:message key="See_liner_photo"/></a></td>
                            <td class="card-text cruise-text">${cruise.start.toString().substring(0,16)}</td>
                            <td class='card-text cruise-text'>${cruise.end.toString().substring(0,16)}</td>
                            <td class="card-text cruise-text">${cruise.price}</td>
                            <td class="card-text cruise-text">${cruise.route.port_amount}</td>
                            <td class="card-text cruise-text">${cruise.route.routeTranslationList.get(0).start}</td>
                            <td class="card-text cruise-text">${cruise.route.routeTranslationList.get(0).end}</td>
                            <td class="card-text cruise-text">${cruise.available}</td>
                            <td class="card-text cruise-text">${cruise.liner.name}</td>
                            <td class="card-text cruise-text">${cruise.liner.capacity}</td>
                            <td class="card-text cruise-text">${cruise.liner.deck_amount}</td>
                            <td class="card-text cruise-text">${cruise.liner.company.title}</td>
                            <c:if test="${sessionScope.name!=null && sessionScope.role == 'User'}">
                            <td>
                                <c:if test="${cruise.available > 0}">
                                    <a class="apply btn btn-success"
                                       href="${pageContext.request.contextPath}/applying?cruise=${cruise.id}">
                                        <fmt:message key="Apply"/>
                                    </a>
                                </c:if>
                                <c:if test="${cruise.available <= 0}">
                                    <a class="apply btn btn-success disabled" role="button"
                                       aria-disabled="true"
                                       href="${pageContext.request.contextPath}/applying?cruise=${cruise.id}">
                                        <fmt:message key="Apply"/>
                                    </a>
                                </c:if>
                            </td>
                            </c:if>
                            <c:if test="${sessionScope.name!=null && sessionScope.role == 'Admin'}">
                            <td>
                                <a class="apply btn btn-info"
                                   href="${pageContext.request.contextPath}/admin/editCruise?cruise=${cruise.id}&start=${cruise.start.toString().substring(0,16).replace(" ", "T")}&end=${cruise.end.toString().substring(0,16).replace(" ", "T")}&price=${cruise.price}&available=${cruise.available}&liner=${cruise.liner.id}&route=${cruise.route.id}">
                                    <fmt:message key="Change"/>
                                </a>
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/admin/deleteCruise?cruise=${cruise.id}"
                                      method="post">
                                    <input type="submit" value="Delete" class="apply btn btn-danger">
                                </form>
                            </td>
                            </c:if>
                    </tr>
                    </tbody>
                </table>
            </div>
        </c:forEach>
        <c:if test="${requestScope.count > 4}">
            <div class="cruise-pages border bg-light">
                <c:forEach var="i" begin="0" end="${requestScope.count/4}">
                    <a class="link-secondary" href="${pageContext.request.contextPath}/?page=${i}">${i+1}</a>
                </c:forEach>
            </div>
        </c:if>
    </div>
</div>
<script>
    setTimeout(function () {
        $('.loader_bg').fadeToggle();
    }, 1500);
</script>
</body>
</html>




