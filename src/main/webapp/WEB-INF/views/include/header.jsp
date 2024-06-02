<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- header -->
<header>
  <div class="inner-header">
    <h1 class="logo">
      <a href="/board/list">
        <img src="/assets/img/logo.png" alt="로고이미지">
      </a>
    </h1>

<%--    requestScope는 모델수송객체에 붙이는데 생략가능해서 그렇게 않했었당--%>
<%--    sessionScope 는 session 을 사용할 때 붙인다. 앞에 이름을 붙이면 --%>
<%--    특정할 수 있고, 특정하지 않으면 더 큰 범위에서 찾을 수 있다. --%>
<%--    중복된 이름이 있을 경우 이름이 겹처서 오류 ~ --%>
    <h2 class="intro-text">Welcome ${login.nickName} </h2>
    <a href="#" class="menu-open">
      <span class="menu-txt">MENU</span>
      <span class="lnr lnr-menu"></span>
    </a>
  </div>

  <nav class="gnb">
    <a href="#" class="close">
      <span class="lnr lnr-cross"></span>
    </a>
    <ul>
      <li><a href="/">Home</a></li>
      <li><a href="#">About</a></li>
      <li><a href="/board/list">Board</a></li>
      <li><a href="#">Contact</a></li>


      <c:if test="${login == null}">
        <li><a href="/members/sign-up">Sign Up</a></li>
        <li><a href="/members/sign-in">Sign In</a></li>
      </c:if>

      <c:if test="${login != null}">
        <li><a href="#">My Page</a></li>
        <li><a href="/members/sign-out">Sign Out</a></li>
      </c:if>
    </ul>
  </nav>

</header>
<!— //header —>