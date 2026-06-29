<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1> 템플릿 입니다! </h1>

<%-- 로그인 안했을 때 -> Authentication에 인증 객체가 없을 때 --%>
<sec:authorize access="isAnonymous()">
    <a href="/security/login">로그인</a><br>
</sec:authorize>

<%-- 로그인 한 경우 -> 인증 객체가 있을 때--%>
<sec:authorize access="isAuthenticated()">
    <h1>사용자명 : <sec:authentication property="principal.username" /></h1>
    <form action="/security/logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="로그아웃"/>
    </form>
</sec:authorize>

<a href="security/admin">관리자 전용 페이지</a>
<br>
<a href="security/member">회원, 관리자 전용 페이지</a>
<br>
<a href="security/all">아무나 접근 가능</a>
</body>
</html>
