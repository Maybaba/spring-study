
// import {fetchReplies, replyPageClickEvent} from "./getReply.js";
import {fetchReplyPost} from "./postReply.js";
import {removeReplyClickEvent} from "./deleteReply.js";
import {fetchInfScrollReplies} from "./getReply.js";
import {modifyReplyClickEvent} from "./modifyReply.js";



// ====== 전역 변수 ========
export const BASE_URL = 'http://localhost:8181/api/v1/replies';

// ====== 실행 코드 ========

// 댓글 목록 서버에서 불러오기
// fetchReplies();
fetchInfScrollReplies();//1페이지 먼저 그려놓기
// setupInfiniteScroll(); //뎃글 무한로딩 페이징

//댓글 작성 이벤트 등록
document.getElementById('replyAddBtn').addEventListener('click', e => {

    //댓글 등록 로직
    fetchReplyPost();
})

//댓글삭제이벤트등록
removeReplyClickEvent();

//댓글 수정 이벤트 등록
modifyReplyClickEvent();

//댓글페이지클릭
// replyPageClickEvent();






