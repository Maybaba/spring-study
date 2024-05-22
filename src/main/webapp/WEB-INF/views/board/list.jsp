<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>

  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Single+Day&display=swap" rel="stylesheet">

  <!-- reset -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">

  <!-- fontawesome css: https://fontawesome.com -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">

  <!-- bootstrap css -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">


  <link rel="stylesheet" href="/assets/css/main.css">
  <link rel="stylesheet" href="/assets/css/list.css">

  <style>
    .card-container .card .card-title-wrapper .time-view-wrapper>div.hit {
      background: yellow;
    }
  </style>

</head>

<body>

<div id="wrap">

  <div class="main-title-wrapper">
    <h1 class="main-title">꾸러기 게시판</h1>
    <button class="add-btn">새 글 쓰기</button>
  </div>

  <div class="card-container">

    <c:forEach var="b" items="${bList}">
      <div class="card-wrapper">
        <section class="card" data-bno="${b.bno}">
          <div class="card-title-wrapper">
            <h2 class="card-title">${b.shortTitle}</h2>
            <div class="time-view-wrapper">
              <div class="time">
                <i class="far fa-clock"></i>
                  ${b.date}
              </div>

              <c:if test="${b.hit}">
                <div class="hit">HIT</div>
              </c:if>

              <c:if test="${b.newArticle}">
                <div class="hit">NEW</div>
              </c:if>

              <div class="view">
                <i class="fas fa-eye"></i>
                <span class="view-count">${b.view}</span>
              </div>
            </div>
          </div>
          <div class="card-content">
              ${b.shortContent}
          </div>
        </section>
        <div class="card-btn-group">
          <button class="del-btn" data-href="/board/delete?bno=${b.bno}">
            <i class="fas fa-times"></i>
          </button>
        </div>
      </div>
      <!-- end div.card-wrapper -->
    </c:forEach>


  </div>
  <!-- end div.card-container -->

  <!-- 게시글 목록 하단 영역 -->
  <div class="bottom-section">

    <!-- 페이지 버튼 영역 -->
    <nav aria-label="Page navigation example">
      <ul class="pagination pagination-lg pagination-custom">

        <c:if test="${maker.pageInfo.pageNo != 1}">
        <li class="page-item">
          <a class="page-link" href="/board/list?pageNo=1"> 처음 </a>
        </c:if>


<%--        1, 10페이지 단위로 넘어가기--%>

      <c:if test="${maker.prev}">
        <li class="page-item">
           <a class="page-link" href="/board/list?pageNo=${maker.begin - 1}"> << </a>
          <li class="page-item">
            <a class="page-link" href="/board/list?pageNo=${page.pageNo - 1}"> prev </a>
          </li>
      </c:if>

        <c:forEach var="i" begin="${maker.begin}" end="${maker.end}">
          <li data-page-num="${i}" class="page-item">
            <a class="page-link" href="/board/list?pageNo=${i}">${i}</a>
          </li>
        </c:forEach>

    <c:if test="${maker.next}">
          <li class="page-item">
      <a class="page-link" href="/board/list?pageNo=${page.pageNo + 1}"> next </a>
        <li class="page-item">
          <a class="page-link" href="/board/list?pageNo=${maker.end + 1}"> >> </a>
        </li>
    </c:if>

          <c:if test="${maker.pageInfo.pageNo != maker.finalPage}">
          <li class="page-item">
            <a class="page-link" href="/board/list?pageNo=${maker.finalPage}"> 끝 </a>
            </c:if>

      </ul>
    </nav>

  </div>
  <!-- end div.bottom-section -->

</div>
<!-- end div.wrap -->


<!-- 모달 창 -->
<div class="modal" id="modal">
  <div class="modal-content">
    <p>정말로 삭제할까요?</p>
    <div class="modal-buttons">
      <button class="confirm" id="confirmDelete"><i class="fas fa-check"></i> 예</button>
      <button class="cancel" id="cancelDelete"><i class="fas fa-times"></i> 아니오</button>
    </div>
  </div>
</div>



<script>

  const $cardContainer = document.querySelector('.card-container');

  //================= 삭제버튼 스크립트 =================//
  const modal = document.getElementById('modal'); // 모달창 얻기
  const confirmDelete = document.getElementById('confirmDelete'); // 모달 삭제 확인버튼
  const cancelDelete = document.getElementById('cancelDelete'); // 모달 삭제 취소 버튼



  $cardContainer.addEventListener('click', e => {
    // 삭제 버튼을 눌렀다면~
    if (e.target.matches('.card-btn-group *')) {
      console.log('삭제버튼 클릭');
      modal.style.display = 'flex'; // 모달 창 띄움

      const $delBtn = e.target.closest('.del-btn');
      // 삭제 링크주소 얻기
      const deleteLocation = $delBtn.dataset.href;
      console.log(deleteLocation);

      // 확인 버튼 이벤트
      confirmDelete.onclick = e => {
        // 삭제 처리 로직
        window.location.href = deleteLocation;

        modal.style.display = 'none'; // 모달 창 닫기
      };


      // 취소 버튼 이벤트
      cancelDelete.onclick = e => {
        modal.style.display = 'none'; // 모달 창 닫기
      };
    } else { // 삭제 버튼 제외한 부분은 글 상세조회 요청

      // section태그에 붙은 글번호 읽기
      const bno = e.target.closest('section.card').dataset.bno;
      // 요청 보내기
      window.location.href = '/board/detail?bno=' + bno;
    }
  });


  // 전역 이벤트로 모달창 닫기
  window.addEventListener('click', e => {
    if (e.target === modal) {
      modal.style.display = 'none';
    }
  });

  //========== 게시물 목록 스크립트 ============//

  function removeDown(e) {
    if (!e.target.matches('.card-container *')) return;
    const $targetCard = e.target.closest('.card-wrapper');
    $targetCard?.removeAttribute('id', 'card-down');
  }

  function removeHover(e) {
    if (!e.target.matches('.card-container *')) return;
    const $targetCard = e.target.closest('.card');
    $targetCard?.classList.remove('card-hover');

    const $delBtn = e.target.closest('.card-wrapper')?.querySelector('.del-btn');
    $delBtn.style.opacity = '0';
  }



  $cardContainer.onmouseover = e => {

    if (!e.target.matches('.card-container *')) return;

    const $targetCard = e.target.closest('.card');
    $targetCard?.classList.add('card-hover');

    const $delBtn = e.target.closest('.card-wrapper')?.querySelector('.del-btn');
    $delBtn.style.opacity = '1';
  }

  $cardContainer.onmousedown = e => {

    if (e.target.matches('.card-container .card-btn-group *')) return;

    const $targetCard = e.target.closest('.card-wrapper');
    $targetCard?.setAttribute('id', 'card-down');
  };

  $cardContainer.onmouseup = removeDown;

  $cardContainer.addEventListener('mouseout', removeDown);
  $cardContainer.addEventListener('mouseout', removeHover);

  // write button event
  document.querySelector('.add-btn').onclick = e => {
    window.location.href = '/board/write';

    //카드 랜덤 색상 뽑기 꼭 써먹기 헤헤 --%>
        function getRandomColor() {
          const colors = ['#188386', '#2529c0', '#bea6ed', '#d71984', '#6AB7E6', '#96E66A'];
          const randomIndex = Math.floor(Math.random() * colors.length);
          return colors[randomIndex];}
  };

  //컨트롤러에서  page 추가함 -> 읽는 값을 가져오기.
  const currentPage = ${page.pageNo};
  //currentPage = li의 data-pageNum이 같을때!
  $pageLinks = document.querySelectorAll('.page-item');

  $pageLinks.forEach(pageLink => {
    const pageNum = pageLink.dataset.pageNum; // data-page-num 속성의 값 가져오기

    if (currentPage === parseInt(pageNum)) { // currentPage와 pageNum을 비교하려면 형변환 필요
      pageLink.classList.add('active'); // 활성화 클래스 추가
    }
  });

  /*

  //선생님알고뤼둠
  function appendActivePage() {
    //1.현재위치한 페이지 번호 알아내기
    //주소창에 파라미터 숫자 읽기 혹은 서버에서 내려준 페이지 번호 읽기
    const cp = '${maker.pageInfo.pageNo}'; <%--EL : jsp 문법--%>
    console.log('현재페이지 ' + cp);

    //2. 해당 페이지 번호와 일치하는 li태그 탐색
    document.querySelector('.pagination li[data-page-num="\${cp}"]'); <%-- template literal : js 문법이라 백슬래시 추가해줘야함 --%>

    //3. 두 번호를 일치시키기
    $li.classList.add('active');
  }
  appendActivePage();

 */

</script>

</body>

</html>