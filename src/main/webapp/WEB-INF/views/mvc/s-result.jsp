<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Web Study</title>
</head>
<body>

<h1 class="result-title">
    <c:if test="${not empty message}">${message}</c:if>


<%--  <c:if test="${result == 'f-id'}">아이디가 존재하지 않습니다. </c:if>--%>
<%--  <c:if test="${result == 'f-pw'}">비밀번호가 틀렸습니다. </c:if>--%>
<%--  <c:if test="${result == 'success'}">로그인 성공</c:if>--%>
</h1>


<a href="/hw/s-login-form">다시 로그인하기</a>

<%-- <script>--%>
<%--    --%>
<%--    let result = '${result}';--%>
<%--        console.log(result);--%>

<%--        const $h1 = document.querySelector('.result-title');--%>
<%--        switch (result) {--%>
<%--            case 'f-id': --%>
<%--                $h1.textContent = '아이디가 존재하지 않습니다.';--%>
<%--                break;--%>
<%--            case 'f-pw':--%>
<%--                $h1.textContent = '비밀번호가 틀렸습니다.';--%>
<%--                break;--%>
<%--            case 'success':--%>
<%--                $h1.textContent = '로그인 성공.';--%>
<%--                break;        --%>
<%--        }--%>
<%-- </script>--%>


</body>
</html>