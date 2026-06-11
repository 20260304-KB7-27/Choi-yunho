<%--
  Created by IntelliJ IDEA.
  User: youknow
  Date: 2026. 6. 11.
  Time: 오후 2:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${score >= 60}" var="testResult" scope="session">
  <p>합격입니다!</p>
</c:if>
<c:if test="${score < 60}">
  <p>불합격입니다.</p>
</c:if>
</body>
</html>
