<%--
  Created by IntelliJ IDEA.
  User: youknow
  Date: 2026. 6. 11.
  Time: 오후 2:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // page 스코프에 데이터 추가
    pageContext.setAttribute("scopeName", "Page Scope 데이터");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>scope 데이터 보기 </h1>

pageScope의 속성값은 : ${pageScope.scopeName}<br>
requestScope의 속성값은 : ${requestScope.scopeName}<br>
sessionScope의 속성값은 : ${sessionScope.scopeName}<br>
applicationScope의 속성값은 : ${applicationScope.scopeName}<br>

member: ${member.name}(${member.userid})<br>
탐색한 결과의 속성값은 : ${scopeName}<br>

<a href="scope2.jsp">scope2</a>
</body>
</html>
