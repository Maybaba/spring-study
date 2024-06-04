<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="../include/static-head.jsp" %>

    <link rel="stylesheet" href="/assets/css/detail.css">
</head>
<body>

<%@ include file="../include/header.jsp" %>

<div id="wrap" class="form-container" data-bno="${bbb.boardNo}">
    <h1>${bbb.boardNo}번 게시물 내용 🎔 </h1>
    <h2># 작성일자: ${bbb.regDateTime}</h2>
    <label for="writer">작성자</label>
    <input type="text" id="writer" name="writer" value="${bbb.writer}" readonly>
    <label for="title">제목</label>
    <input type="text" id="title" name="title" value="${bbb.title}" readonly>
    <label for="content">내용</label>
    <div id="content">
        <iframe width="560" height="315" src="https://www.youtube.com/embed/35AgDDPQE48?si=l3jMFyL-16PetUN_" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
        ${bbb.content}
    </div>


    <div class="buttons">
        <div class="reaction-buttons">
            <button id="like-btn">
                <i class="fas fa-thumbs-up"></i> 좋아요
                <span id="like-count">0</span>
            </button>
            <button
                    id="dislike-btn"
                    class="dislike-btn"
            >
                <i class="fas fa-thumbs-down"></i> 싫어요
                <span id="dislike-count">0</span>
            </button>
        </div>

        <button
                class="list-btn"
                type="button"
                onclick="window.location.href='${ref}'"
        >
            목록
        </button>
    </div>


    <!-- 댓글 영역 -->
    <div id="replies" class="row">
        <div class="offset-md-1 col-md-10">
            <!-- 댓글 쓰기 영역 -->
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-9">
                            <div class="form-group">
                                <label for="newReplyText" hidden>댓글 내용</label>
                                <textarea rows="3" id="newReplyText" name="replyText" class="form-control" placeholder="댓글을 입력해주세요."></textarea>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <label for="newReplyWriter" hidden>댓글 작성자</label>
                                <input id="newReplyWriter" name="replyWriter" type="text" class="form-control" placeholder="작성자 이름" style="margin-bottom: 6px;">
                                <button id="replyAddBtn" type="button" class="btn btn-dark form-control">등록</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- end reply write -->

            <!--댓글 내용 영역-->
            <div class="card">
                <!-- 댓글 내용 헤더 -->
                <div class="card-header text-white m-0" style="background: #343A40;">
                    <div class="float-left">댓글 (<span id="replyCnt"></span>)</div>
                </div>

                <!-- 댓글 내용 바디 -->
                <div id="replyCollapse" class="card">
                    <div id="replyData">
                        <!-- JS로 댓글 정보 DIV삽입 -->
                    </div>

                    <!-- 댓글 페이징 영역 -->
                    <!-- <ul class="pagination justify-content-center"> -->
                    <!-- JS로 댓글 페이징 DIV삽입 -->
                    <!-- </ul> -->
                </div>
            </div> <!-- end reply content -->
        </div>
    </div> <!-- end replies row -->

    <!-- 댓글 수정 모달 -->
    <div class="modal fade bd-example-modal-lg" id="replyModifyModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header" style="background: #343A40; color: white;">
                    <h4 class="modal-title">댓글 수정하기</h4>
                    <button type="button" class="close text-white" data-bs-dismiss="modal">X</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <div class="form-group">
                        <input id="modReplyId" type="hidden">
                        <label for="modReplyText" hidden></label>
                        <textarea id="modReplyText" class="form-control" placeholder="내내냉" rows="3"></textarea>
                    </div>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button id="replyModBtn" type="button" class="btn btn-dark">수정</button>
                    <button id="modal-close" type="button" class="btn btn-danger" data-bs-dismiss="modal">닫기</button>
                </div>
            </div>
        </div>
    </div>
    <!-- end replyModifyModal -->

    <!-- 로딩 스피너 -->
    <div class="spinner-container" id="loadingSpinner">
        <div class="spinner-border text-light" role="status">
            <span class="visually-hidden">Loading...</span>
        </div>
    </div>
</div>

<script type="module" src="/assets/js/reply.js"></script>

<script>

    // 서버에 좋아요, 싫어요 요청을 보내는 함수
    async function sendReaction(reactionType) {
        console.log(reactionType);
        const bno = document.getElementById('wrap').dataset.bno;

        const res = await fetch(`/board/\${reactionType}?bno=\${bno}`);
        const { likeCount, dislikeCount, userReaction } = await res.json();

        document.getElementById('like-count').textContent = likeCount;
        document.getElementById('dislike-count').textContent = dislikeCount;

        // console.log(json);

        //버튼 활성화 스타일 처리
        updateReactionButtons(userReaction);
    }

    //좋실어요 버튼 배경색 변경
    function updateReactionButtons(userReaction) {
        const $likeBtn = document.getElementById('like-btn');
        const $dislikeBtn = document.getElementById('dislike-btn');

        //좋아요 버튼이 눌렸을 경우
        if(userReaction === 'LIKE') {
            $likeBtn.classList.add('active');
            $dislikeBtn.classList.remove('active');

        } else if(userReaction === 'DISLIKE') { //싫어요 버튼이 눌렸을 경우
            $dislikeBtn.classList.add('active');
            $likeBtn.classList.remove('active');

        } else { //둘다 안눌렸을 경우
            $likeBtn.classList.remove('active');
            $dislikeBtn.classList.remove('active');

        }
    }

    // 좋아요 클릭 이벤트
    document.getElementById('like-btn').addEventListener('click', e => {
        sendReaction('like');
    });

    // 싫어요 클릭 이벤트
    document.getElementById('dislike-btn').addEventListener('click', e => {
        sendReaction('dislike');
    });
</script>
</body>
</html>


<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html lang="ko">--%>
<%--<head>--%>
<%--  <%@ include file="../include/static-head.jsp" %>--%>
<%--  <style>--%>


<%--    .form-container {--%>
<%--      width: 500px;--%>
<%--      margin: auto;--%>
<%--      padding: 20px;--%>
<%--      background-image: linear-gradient(135deg, #a1c4fd, #fbc2eb);--%>
<%--      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);--%>
<%--      border-radius: 4px;--%>
<%--      font-size: 18px;--%>
<%--    }--%>
<%--    .form-container h1 {--%>
<%--      font-size: 40px;--%>
<%--      font-weight: 700;--%>
<%--      letter-spacing: 10px;--%>
<%--      text-align: center;--%>
<%--      margin-bottom: 20px;--%>
<%--      color: #ffffff;--%>
<%--    }--%>
<%--    .form-container h2 {--%>
<%--      font-size: 30px;--%>
<%--      color: #222;--%>
<%--      text-align: center;--%>
<%--      margin-bottom: 20px;--%>
<%--    }--%>
<%--    label {--%>
<%--      display: block;--%>
<%--      margin-bottom: 5px;--%>
<%--      font-size: 20px;--%>
<%--    }--%>

<%--    #title, #writer {--%>
<%--      font-size: 18px;--%>
<%--      width: 100%;--%>
<%--      padding: 8px;--%>
<%--      box-sizing: border-box;--%>
<%--      border: 2px solid #ffffff;--%>
<%--      border-radius: 8px;--%>
<%--      margin-bottom: 10px;--%>
<%--      background-color: rgba(255, 255, 255, 0.8);--%>
<%--    }--%>
<%--    #content {--%>
<%--      height: 400px;--%>
<%--      font-size: 18px;--%>
<%--      width: 100%;--%>
<%--      padding: 8px;--%>
<%--      box-sizing: border-box;--%>
<%--      border: 2px solid #ffffff;--%>
<%--      border-radius: 8px;--%>
<%--      margin-bottom: 10px;--%>
<%--      background-color: rgba(255, 255, 255, 0.8);--%>
<%--    }--%>

<%--    textarea {--%>
<%--      resize: none;--%>
<%--      height: 200px;--%>
<%--    }--%>
<%--    .buttons {--%>
<%--      display: flex;--%>
<%--      justify-content: flex-end;--%>
<%--      margin-top: 20px;--%>
<%--    }--%>
<%--    button {--%>
<%--      font-size: 20px;--%>
<%--      padding: 10px 20px;--%>
<%--      border: none;--%>
<%--      margin-right: 10px;--%>
<%--      background-color: #4CAF50;--%>
<%--      color: white;--%>
<%--      cursor: pointer;--%>
<%--      border-radius: 4px;--%>
<%--      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);--%>
<%--      transition: background-color 0.3s;--%>
<%--    }--%>
<%--    button.list-btn {--%>
<%--      background: #e61e8c;--%>
<%--    }--%>
<%--    button:hover {--%>
<%--      background-color: #3d8b40;--%>
<%--    }--%>
<%--    button.list-btn:hover {--%>
<%--      background: #e61e8c93;--%>
<%--    }--%>

<%--    /* 페이지 css */--%>
<%--    /* 페이지 액티브 기능 */--%>
<%--    .pagination .page-item.p-active a {--%>
<%--      background: #333 !important;--%>
<%--      color: #fff !important;--%>
<%--      cursor: default;--%>
<%--      pointer-events: none;--%>
<%--    }--%>

<%--    .pagination .page-item:hover a {--%>
<%--      background: #888 !important;--%>
<%--      color: #fff !important;--%>
<%--    }--%>
<%--    .spinner-container {--%>
<%--      display: none;--%>
<%--      justify-content: center;--%>
<%--      align-items: center;--%>
<%--      position: fixed;--%>
<%--      top: 0;--%>
<%--      left: 0;--%>
<%--      width: 100%;--%>
<%--      height: 100%;--%>
<%--      background-color: rgba(0, 0, 0, 0.5);--%>
<%--      z-index: 1050;--%>
<%--    }--%>


<%--  </style>--%>
<%--</head>--%>
<%--<body>--%>

<%--<%@ include file="../include/header.jsp" %>--%>

<%--<div id="wrap" class="form-container" data-bno="${bbb.boardNo}">--%>

<%--  <h1>${bbb.boardNo}번 게시물 내용 🎔 </h1>--%>
<%--  <h2># 작성일자: ${bbb.regDateTime}</h2>--%>
<%--  <label for="writer">작성자</label>--%>
<%--  <input type="text" id="writer" name="writer" value="${bbb.writer}" readonly>--%>
<%--  <label for="title">제목</label>--%>
<%--  <input type="text" id="title" name="title" value="${bbb.title}" readonly>--%>
<%--  <label for="content">내용</label>--%>
<%--  <div id="content">--%>
<%--    <iframe width="560" height="315" src="https://www.youtube.com/embed/35AgDDPQE48?si=l3jMFyL-16PetUN_" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>--%>
<%--    ${bbb.content}--%>
<%--  <div class="buttons">--%>
<%--    <button class="list-btn" type="button" onclick="window.location.href='${ref}'">목록</button>--%>
<%--  </div>--%>

<%--    <!-- 좋아요 버튼 영역-->--%>

<%--    <div class="buttons">--%>
<%--      <!-- 좋아요 버튼 -->--%>
<%--      <button id="likeBtn" class="like-btn" type="button" onclick="like()">--%>
<%--        <!-- 하트 아이콘 -->--%>
<%--        <i class="fas fa-heart"></i>--%>
<%--        <!-- 좋아요 텍스트 -->--%>
<%--      </button>--%>
<%--      <!-- 좋아요 수 표시 -->--%>
<%--&lt;%&ndash;      <span id="likeCount">${bbb.likeCount}</span/>&ndash;%&gt;--%>
<%--    </div>--%>

<%--  <!-- 댓글 영역 -->--%>

<%--  <div id="replies" class="row">--%>
<%--    <div class="offset-md-1 col-md-10">--%>
<%--      <!-- 댓글 쓰기 영역 -->--%>
<%--      <div class="card">--%>
<%--        <div class="card-body">--%>
<%--          <div class="row">--%>
<%--            <div class="col-md-9">--%>
<%--              <div class="form-group">--%>
<%--                <label for="newReplyText" hidden>댓글 내용</label>--%>
<%--                <textarea rows="3" id="newReplyText" name="replyText" class="form-control"--%>
<%--                          placeholder="댓글을 입력해주세요."></textarea>--%>
<%--              </div>--%>
<%--            </div>--%>
<%--            <div class="col-md-3">--%>
<%--              <div class="form-group">--%>
<%--                <label for="newReplyWriter" hidden>댓글 작성자</label>--%>
<%--                <input id="newReplyWriter" name="replyWriter" type="text"--%>
<%--                       class="form-control" placeholder="작성자 이름"--%>
<%--                       style="margin-bottom: 6px;">--%>
<%--                <button id="replyAddBtn" type="button"--%>
<%--                        class="btn btn-dark form-control">등록--%>
<%--                </button>--%>
<%--              </div>--%>
<%--            </div>--%>
<%--          </div>--%>
<%--        </div>--%>
<%--      </div> <!-- end reply write -->--%>

<%--      <!--댓글 내용 영역-->--%>
<%--      <div class="card">--%>
<%--        <!-- 댓글 내용 헤더 -->--%>
<%--        <div class="card-header text-white m-0" style="background: #343A40;">--%>
<%--          <div class="float-left">댓글 (<span id="replyCnt"></span>)</div>--%>
<%--        </div>--%>

<%--        <!-- 댓글 내용 바디 -->--%>
<%--        <div id="replyCollapse" class="card">--%>
<%--          <div id="replyData">--%>
<%--            <!----%>
<%--            < JS로 댓글 정보 DIV삽입 >--%>
<%--        -->--%>
<%--          </div>--%>


<%--&lt;%&ndash;          <!-- 댓글 페이징 영역 -->&ndash;%&gt;--%>
<%--&lt;%&ndash;          <ul class="pagination justify-content-center">&ndash;%&gt;--%>
<%--&lt;%&ndash;            <!--&ndash;%&gt;--%>
<%--&lt;%&ndash;            < JS로 댓글 페이징 DIV삽입 >&ndash;%&gt;--%>
<%--&lt;%&ndash;        -->&ndash;%&gt;--%>
<%--&lt;%&ndash;          </ul>&ndash;%&gt;--%>
<%--&lt;%&ndash;        </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;      </div> <!-- end reply content -->&ndash;%&gt;--%>
<%--&lt;%&ndash;    </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;  </div> <!-- end replies row -->&ndash;%&gt;--%>

<%--  <!-- 댓글 수정 모달 -->--%>
<%--  <div class="modal fade bd-example-modal-lg" id="replyModifyModal">--%>
<%--    <div class="modal-dialog modal-lg">--%>
<%--      <div class="modal-content">--%>

<%--        <!-- Modal Header -->--%>
<%--        <div class="modal-header" style="background: #343A40; color: white;">--%>
<%--          <h4 class="modal-title">댓글 수정하기</h4>--%>
<%--          <button type="button" class="close text-white" data-bs-dismiss="modal">X</button>--%>
<%--        </div>--%>

<%--        <!-- Modal body -->--%>
<%--        <div class="modal-body">--%>
<%--          <div class="form-group">--%>
<%--            <input id="modReplyId" type="hidden">--%>
<%--            <label for="modReplyText" hidden></label>--%>
<%--            <textarea id="modReplyText" class="form-control" placeholder="내내냉"--%>
<%--                      rows="3"></textarea>--%>
<%--          </div>--%>
<%--        </div>--%>

<%--        <!-- Modal footer -->--%>
<%--        <div class="modal-footer">--%>
<%--          <button id="replyModBtn" type="button" class="btn btn-dark">수정</button>--%>
<%--          <button id="modal-close" type="button" class="btn btn-danger"--%>
<%--                  data-bs-dismiss="modal">닫기--%>
<%--          </button>--%>
<%--        </div>--%>
<%--      </div>--%>
<%--    </div>--%>
<%--  </div>--%>

<%--&lt;%&ndash; end replyModifyModal &ndash;%&gt;--%>

<%--</div>--%>
<%--        <!-- 로딩 스피너 -->--%>
<%--        <div class="spinner-container" id="loadingSpinner">--%>
<%--          <div class="spinner-border text-light" role="status">--%>
<%--            <span class="visually-hidden">Loading...</span>--%>
<%--          </div>--%>
<%--        </div>--%>

<%--<!-- 0.댓글 영역 -->--%>

<%--&lt;%&ndash;<div>&ndash;%&gt;--%>
<%--&lt;%&ndash;<c:forEach var="r" items="${bbb.replies}">&ndash;%&gt;--%>
<%--&lt;%&ndash;  <div style="font-size: 24px;">${r}</div>&ndash;%&gt;--%>
<%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
<%--&lt;%&ndash;  <c:out value="${bbb}"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;  <c:out value="${bbb.replies}"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;</div>&ndash;%&gt;--%>

<%--&lt;%&ndash;자바코드는 %로 감싸야 하고 jsp는 ! 로 써야한다. 안그러면 에러남 ㅠ &ndash;%&gt;--%>

<%--<script type="module" src="/assets/js/reply.js"></script>--%>
<%--        <script>--%>
<%--          // 좋아요 버튼 클릭 시 호출되는 함수--%>
<%--          function like() {--%>
<%--            // 여기에는 실제로 서버로 요청을 보내서 DB를 업데이트하는 로직이 들어갑니다.--%>

<%--            // 여기서는 단순히 버튼의 스타일을 변경하여 하트를 채우는 예시를 보여줍니다.--%>
<%--            let likeBtn = document.getElementById("likeBtn");--%>
<%--            // var likeCount = document.getElementById("likeCount");--%>

<%--            // 버튼 색상과 아이콘 변경--%>
<%--            likeBtn.style.backgroundColor = "#e61e8c"; // 분홍색--%>

<%--            // 좋아요 수 증가--%>
<%--            // likeCount.innerText = parseInt(likeCount.innerText) + 1;--%>
<%--          }--%>
<%--        </script>--%>





<%--</body>--%>
<%--</html>--%>

