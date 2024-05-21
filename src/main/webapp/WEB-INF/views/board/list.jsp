<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<%--  리스트에 있는 걸 모두 해당 html로 감싼 뒤, 보여주기--%>
  <c:forEach var="d" items="${boardList}">
  <div class="card-container">

    <div class="card-wrapper">
      <section class="card" data-bno=${d.boardNo}>
        <div class="card-title-wrapper">
          <h2 class="card-title">${d.title}</h2>
          <div class="time-view-wrapper">
            <div class="time">
              <i class="far fa-clock"></i>
                ${d.regDateTime}</div>

<%--            <c:if test="${b.hit}">--%>
<%--              <div class="hit">HIT</div>--%>
<%--            </c:if>--%>

<%--            <c:if test="${b.newArticle}">--%>
<%--              <div class="hit">NEW</div>--%>
<%--            </c:if>--%>

            <div class="view">
              <i class="fas fa-eye"></i>
              <span class="view-count">${d.viewCount}</span>
            </div>
          </div>
        </div>
        <div class="card-content">

         ${d.content}

        </div>
      </section>
      <div class="card-btn-group">
        <button class="del-btn" data-href="/board/delete?bno=${d.boardNo}">
          <i class="fas fa-times"></i>
        </button>
      </div>
    </div>

  </div>
  </c:forEach>

</div>


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

하나의 카드에만 script 적용되는 문제해결
1. 카드 전체에 eventListener 함수 적용되도록 변경
2. 버튼 누르는 이벤트리스너 함수 forEach($cardContainer =>) 로 nodeList 형식에 맞게 배열순회 적용하여 이벤트 추가

하나의 동일한 카드 디자인이 세로로 추가되는 문제해결

<script>

  // 'write' 버튼에 클릭 이벤트를 추가하고, 페이지를 '/board/write'로 이동합니다.
  document.querySelector('.add-btn').onclick = e => {
    window.location.href = '/board/write';
  };

  document.addEventListener('DOMContentLoaded', () => {
    const $cardContainers = document.querySelectorAll('.card-container');

    if ($cardContainers.length == 0) return; // card 없으면 이벤트리스너 함수 종료

    //================= 삭제버튼 스크립트 =================//
    const modal = document.getElementById('modal'); // 모달창 얻기
    const confirmDelete = document.getElementById('confirmDelete'); // 모달 삭제 확인버튼
    const cancelDelete = document.getElementById('cancelDelete'); // 모달 삭제 취소 버튼

    $cardContainers.forEach($cardContainer => {
      $cardContainer.addEventListener('click', e => {
        // 삭제 버튼을 눌렀다면~
        if (e.target.matches('.card-btn-group *')) {
          console.log('삭제버튼 클릭');
          modal.style.display = 'flex'; // 모달 창 띄움

          const $delBtn = e.target.closest('.del-btn');
          const deleteLocation = $delBtn.dataset.href;

          // 확인 버튼 이벤트
          confirmDelete.onclick = e => {
            // 삭제 처리 요청 보내기
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

      //========== 게시물 목록 스크립트 ============//

      // 아래 함수는 마우스 다운 이벤트 발생 시 카드의 아이디를 제거합니다.
      function removeDown(e) {
        if (!e.target.matches('.card-container *')) return; // 이벤트가 카드 내부에서 발생하지 않으면 종료합니다.
        const $targetCard = e.target.closest('.card-wrapper'); // 클릭된 카드 요소를 찾습니다.
        $targetCard?.removeAttribute('id', 'card-down'); // 카드의 아이디 속성을 제거합니다.
      }

      // 아래 함수는 호버 이벤트 발생 시 카드의 호버 클래스와 삭제 버튼의 투명도를 조절합니다.
      function removeHover(e) {
        if (!e.target.matches('.card-container *')) return; // 이벤트가 카드 내부에서 발생하지 않으면 종료합니다.
        const $targetCard = e.target.closest('.card'); // 호버 이벤트가 발생한 카드 요소를 찾습니다.
        $targetCard?.classList.remove('card-hover'); // 카드의 호버 클래스를 제거합니다.

        // 삭제 버튼을 찾아 투명도를 0으로 조절합니다.
        const $delBtn = e.target.closest('.card-wrapper')?.querySelector('.del-btn');
        $delBtn.style.opacity = '0';
      }

      // 카드 컨테이너에 마우스 오버 이벤트를 추가합니다.
      $cardContainer.onmouseover = e => {
        if (!e.target.matches('.card-container *')) return; // 이벤트가 카드 내부에서 발생하지 않으면 종료합니다.
        const $targetCard = e.target.closest('.card'); // 호버 이벤트가 발생한 카드 요소를 찾습니다.
        $targetCard?.classList.add('card-hover'); // 카드에 호버 클래스를 추가합니다.

        // 삭제 버튼을 찾아 투명도를 1로 조절합니다.
        const $delBtn = e.target.closest('.card-wrapper')?.querySelector('.del-btn');
        $delBtn.style.opacity = '1';
      };

      // 카드 컨테이너에 마우스 다운 이벤트를 추가합니다.
      $cardContainer.onmousedown = e => {
        if (e.target.matches('.card-container .card-btn-group *')) return; // 삭제 버튼을 클릭한 경우 종료합니다.
        const $targetCard = e.target.closest('.card-wrapper'); // 마우스 다운 이벤트가 발생한 카드 요소를 찾습니다.
        $targetCard?.setAttribute('id', 'card-down'); // 카드에 아이디를 추가합니다.
      };

      // 카드 컨테이너에 마우스 업 이벤트를 추가하고, removeDown 함수를 호출합니다.
      $cardContainer.onmouseup = removeDown;

      // 카드 컨테이너에 마우스 아웃 이벤트를 추가하고, removeDown과 removeHover 함수를 호출합니다.
      $cardContainer.addEventListener('mouseout', removeDown);
      $cardContainer.addEventListener('mouseout', removeHover);
    });

    // 전역 이벤트로 모달창 닫기
    window.addEventListener('click', e => {
      if (e.target === modal) {
        modal.style.display = 'none';
      }
    });


    // function getRandomColor() {
    //   const letters = '0123456789ABCDEF';
    //   let color = '#';
    //   for (let i = 0; i < 6; i++) {
    //     color += letters[Math.floor(Math.random() * 16)];
    //   }
    //   return color;
    //
    // }
    //카드 랜덤 색상 뽑기
    function getRandomColor() {
      const colors = ['#188386', '#2529c0', '#bea6ed', '#d71984', '#6AB7E6', '#96E66A'];
      const randomIndex = Math.floor(Math.random() * colors.length);
      return colors[randomIndex];
    }

    //카드의 색상 지정하기
    document.querySelectorAll('.card-wrapper').forEach(card => {
      const cardTitleWrapper = card.querySelector('.card-title-wrapper');
      cardTitleWrapper.style.backgroundColor = getRandomColor();
    });
    document.querySelectorAll('.card-hover.card').forEach(card => {
      const cardTitleWrapper = card.querySelector('.card-wrapper');
      cardTitleWrapper.style.backgroundColor = getRandomColor();
    });


  });
</script>

</body>

</html>