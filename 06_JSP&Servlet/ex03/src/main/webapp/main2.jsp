<%--
  Created by IntelliJ IDEA.
  User: youknow
  Date: 2026. 6. 10.
  Time: 오후 1:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>메인 화면 페이지</h1>
    <h2>include 지시어 태그 연습</h2>

    <%-- 정적 include --%>
    <%@include file="copywrite.jsp"%>

    <%-- 액션 태그 --%>
    <%-- 동적 include--%>
    <jsp:include page="header2.jsp" flush="true">
        <jsp:param name="nickName" value="youknow"/>
    </jsp:include>
</body>
</html>
