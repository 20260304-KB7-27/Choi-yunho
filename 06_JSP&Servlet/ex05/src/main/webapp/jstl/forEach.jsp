<%--
  Created by IntelliJ IDEA.
  User: youknow
  Date: 2026. 6. 11.
  Time: 오후 2:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3> 조건물 결과</h3>
조건 결과 : ${sessionScope.testResult};

 <c:forEach var="m" items="${memberList}">
   <li>${m.name} : ${m.userid}</li>
 </c:forEach>
</body>
</html>
