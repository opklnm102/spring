<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Login</title>
</head>
<body>
<h1>Login</h1>
<form action="/j_security_check" method="post">
    <input type="text" name="j_username" placeholder="Id"><br>
    <input type="text" name="j_password" placeholder="Password"><br>
    <button type="submit">로그인</button>
</form>
</body>
</html>