<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 목록</title>
    <style>
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f0f0f0;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>
<h2>게시글 목록</h2>

<table border='1'>
    <thead>
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>조회수</th>
        <th>등록일</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="board">
        <tr>
            <td>${board.no}</td>
            <td><a href='/board/detail?no=${board.no}'>${board.title}</a></td>
            <td>${board.writer.name}</td>
            <td>${board.viewCount}</td>
            <td>${board.createdDate}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<a href="/board/form">새 글 작성</a>
</body>
</html>