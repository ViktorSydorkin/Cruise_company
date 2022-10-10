<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/form-style.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <style>
        <%@include file="css/form-style.css"%>
    </style>
    <title>Application</title>
</head>

<body>
<main>
    <div class="circle"></div>
    <div class="register-form-container">
        <form action="${pageContext.request.contextPath}/applying" method="post" enctype="multipart/form-data">
            <h1 class="form-title">
                Application
            </h1>
            <div class="form-fields">
                <div class="form-field">
                    <p style="text-align: center">Yuo are making application to the First cruise</p>
                </div>
                <div class="input_wrapper">
                    <input name="file" type="file" id="file" class="input file" accept="image/png, image/jpg" max-size>
                    <label for="file" class="file-button">
                        <span class="file-button-text">Choose photo/scan</span>
                    </label>
                </div>
            </div>
            <div class="form-buttons">
                <input type="submit" class="button" value="Apply">
            </div>
        </form>
    </div>
</main>
</body>
</html>

