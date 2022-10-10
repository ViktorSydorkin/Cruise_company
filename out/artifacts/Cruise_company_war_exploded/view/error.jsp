<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>Access denied</title>
</head>
<body>
<!------ Include the above in your HEAD tag ---------->

<div class="d-flex justify-content-center align-items-center" style="height: 100vh;">
    <h1 class="mr-3 pr-3 align-top border-right inline-block align-content-center">404</h1>
    <div class="inline-block align-middle">
        <h2 class="font-weight-normal lead" id="desc">The page you requested was not found.</h2>
        <a class="font-weight-normal lead" style="color: #2e2e2e" href="${pageContext.request.contextPath}/">Go to home page</a>
    </div>
</div>
</body>
</html>
