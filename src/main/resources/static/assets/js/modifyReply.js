
import {BASE_URL} from "./reply.js";
import {fetchInfScrollReplies} from "./getReply.js";

//댓글 수정 비동기 요청 처리 함수
// const fetchModifyReply = async (rno) => {
//     const res = await fetch(`${BASE_URL}/${rno}`, {
//         method: 'MODIFY'
//     });
//
//     if (res.status != 200) {
//         alert( '수정에 실패했습니다 ㅜ ');
//         return;
//         }
//
//     fetchInfScrollReplies(); //삭제 후 댓글 페이지 재로딩하기
//     window.scrollTo(0,0); // 삭제 후 페이지 상단으로 이동
// }

export function modifyReplyClickEvent() {
    document.getElementById('replyData').addEventListener('click', e => {
        e.preventDefault(); //A 태그 삭제

        if (!e.target.matches('#replyModBtn')) return;

        console.log(e.target.closest('.row').firstElementChild.textContent);

        // col-md-9 클래스를 가진 요소의 텍스트 내용을 가져옴
        var placeholderText = e.target.closest('.row').firstElementChild.textContent;

        // modReplyText ID를 가진 textarea의 placeholder 속성에 텍스트를 설정
        document.getElementById('modReplyText').setAttribute('placeholder', placeholderText);
        //수정요청 class : btn-dark
        // const rno = e.target.closest('#replyContent').dataset.replyId;
        // fetchModifyReply(rno);
        });
}