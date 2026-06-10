<%--
  Created by IntelliJ IDEA.
  User: youknow
  Date: 2026. 6. 10.
  Time: 오후 2:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Session 실습</h2>
<form method="post" action="session-save">
    값 입력: <input type="text" name="sessionValue">
    <input type="submit" value="저장">
</form>
<form method="post" action="session-delete">
    <input type="submit" value="삭제">
</form>

<hr>

<h2>Cookie 실습</h2>
<form method="post" action="cookie-save">
    값 입력: <input type="text" name="cookieValue">
    <input type="submit" value="저장 ">
</form>
<form method="post" action="cookie-delete">
    <input type="submit" value="삭제">
</form>

<a href="info">현재 session/cookie 값 확인</a>
</body>
</html>