<%--
  Created by IntelliJ IDEA.
  User: youknow
  Date: 2026. 6. 12.
  Time: 오후 2:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todo 목록</title>
</head>
<body>
<h1>Todo 목록 보기</h1>
<div>
    ${todoList}
    <a href="view">상세보기</a>
</div>
<div>
    <a href="create">새 Todo</a>
</div>
</body>
</html>