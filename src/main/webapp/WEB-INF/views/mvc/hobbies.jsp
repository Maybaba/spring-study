<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Web Study</title>
</head>
<body>
<h1>[${name}] 취미 목록</h1>
<ol>
  <%-- for (String h : hList) html 으로는 먹히지 않는다. --%>
  <c:forEach var="h" items="${hobbies}">
    <%--리스트의 목록만큼 출력하기 --%>
    <li>${h}</li>
  </c:forEach>
</ol>

<h2>My major [${major}]</h2>


</body>
</html>