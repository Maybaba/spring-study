<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <style>
    li {
      color: red;
    }
  </style>
</head>
<body>

<ul>

  <h1>프론트컨트롤러 V5 목록보기</h1>

<c:forEach var="m" items="${memberList}">   <%-- 서버에서 보낸 값을 그대로 item으로 받는다--%>
    <li>
      # 아이디: ${m.account},<%-- 원래는 get 해야 하는데 굳이 하지 않아도 알아서 getter를 호출한다. --%>
<%--      <a href="/chap02/v5/detail/account???????????????????????">--%>
      <a href="#">
        이름: ${m.userName}
      </a>
<%--      &nbsp;&nbsp;&nbsp;삭제를 누른 계정명을 해당 클래스로 전달--%>
      <a id="rm-btn" href="/chap02/v5/delete?account=${m.account}">[delete]</a>

    </li>
  </c:forEach>

</ul>

<a href="/chap02/v5/join">새로운 회원가입하기</a>

</body>
</html>