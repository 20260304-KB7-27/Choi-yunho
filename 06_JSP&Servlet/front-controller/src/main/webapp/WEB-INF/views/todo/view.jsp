<%--
  Created by IntelliJ IDEA.
  User: youknow
  Date: 2026. 6. 12.
  Time: 오후 2:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todo 상세</title>
</head>
<body>
<h1>Todo 보기</h1>
<div>
    <a href="list">목록보기</a> |
    <a href="update">수정하기</a>
</div>
<form action="delete" method="POST">
    <input type="submit" value="삭제">
</form>
</body>
</html>
