<%--
  Created by IntelliJ IDEA.
  ru.arthur.webserver.model
  Date: 23.06.2018
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>My super project!</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body class="w3-light-grey">
<!-- header -->
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Super Web Server!</h1>
</div>
<div class="w3-container w3-padding">
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-center w3-green">
            <h2>Login to Admin panel!</h2>
        </div>
            <form method="Get">
                <label>Name:
                    <input type="text" name="login"  required class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
                </label>

                <label>Password:
                    <input type="password" name="pass" required class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
                </label>
                <button type="submit" formaction="/admin/list" class="w3-btn w3-green w3-round-large w3-margin-bottom">Submit</button>
            </form>
    </div>
</div>
</div>
<div>
<div id="error">
    <div id="okno">
        Введен неверный логин или пароль!<br>
        <a href="#" class="close">Закрыть окно</a>
    </div>
</div>
<div id="invalidUser">
    <div id="okno1">
        У данного пользователя недостаточно прав!<br>
        <a href="#" class="close">Закрыть окно</a>
    </div>
</div>
</div>
</body>
</html>
