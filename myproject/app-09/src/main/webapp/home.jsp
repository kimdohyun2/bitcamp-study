<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
trimDirectiveWhitespaces="true" %>
<%@ page import="bitcamp.myapp.member.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>홈</title>
</head>
<body>
<div>
<div>
<c:if test="${not empty loginUser}">
    <p>${loginUser.name}</p>
    <a href='/app/auth/logout'>로그아웃</a>
</c:if>
<c:if test="${empty loginUser}">
    <a href='/app/auth/login-form'>로그인</a>
</c:if>
</div>
<div>
    <h1>강의 관리 시스템</h1>
    <p>환영합니다!</p>
    <ul>
        <li><a href="/app/board/list">게시판</a></li>
    </ul>
</div>
</div>
</body>
</html>