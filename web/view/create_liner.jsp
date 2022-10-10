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
    <title>Create liner</title>
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
        <form action="${pageContext.request.contextPath}/admin/create_liner" method="post" enctype="multipart/form-data">
            <h1 class="form-title">
                New liner
            </h1>
            <div class="form-fields">
                <div class="form-field">
                    <input name="liner_name" type="text" placeholder="Liner name" required
                           pattern="^[A-Z][a-z' ]{1,}">
                </div>
                <div class="form-field">
                    <input name="capacity" type="number" min="1" placeholder="Capacity" required>
                </div>
                <div class="form-field">
                    <input name="deck_amount" type="number" min="1" placeholder="Deck amount" required>
                </div>
                <div class="form-field">
                    <input name="company" list="companies" placeholder="Company" required>
                    <datalist id="companies">
                        <c:forEach var="company" items="${requestScope.companyList}">
                        <option value="${company.id}">
                            </c:forEach>
                    </datalist>
                </div>
                <div class="input_wrapper">
                    <input name="file" type="file" id="file" class="input file" accept="image/png, image/jpg" required>
                    <label for="file" class="file-button">
                        <span class="file-button-text">Choose liner's photo</span>
                    </label>
                </div>
            </div>
            <div class="form-buttons">
                <input type="submit" class="button" value="Create">
            </div>
        </form>
    </div>
</main>
</body>
</html>
