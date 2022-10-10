<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="css/index-style.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <title>Validation</title>
</head>
<body>
<div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Cruise</th>
            <th>Photo</th>
            <th colspan="2">State</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${requestScope.app.id}</td>
            <td>${requestScope.app.cruise.id}</td>
            <td><a href="${pageContext.request.contextPath}/admin/validateImage?app_id=${requestScope.app.id}" target="_blank" class="link-danger">Show passport photo</a></td>
            <td>
                <form action="${pageContext.request.contextPath}/admin/validate?v=true&app_id=${requestScope.app.id}" method="post">
                    <input type="submit" value="Approve" class="btn btn-success">
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/admin/validate?v=false&app_id=${requestScope.app.id}" method="post">
                    <input type="submit" value="Decline" class="btn btn-danger">
                </form>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
