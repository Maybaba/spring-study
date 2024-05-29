
import { BASE_URL } from "./reply.js";
import {fetchInfScrollReplies} from "./getReply.js";


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

//댓글 수정 비동기 요청 처리 함수
export function modifyReplyClickEvent() {

    document.getElementById('replyData').addEventListener('click', e => {
        e.preventDefault(); //A 태그 삭제

        if (!e.target.matches('#replyModBtn')) return;

        console.log(e.target.closest('.row').firstElementChild.textContent);

        // col-md-9 클래스를 가진 요소의 텍스트 내용을 가져옴
        var placeholderText = e.target.closest('.row').firstElementChild.textContent;

        // modReplyText ID를 가진 textarea의 placeholder 속성에 텍스트를 설정
        document.getElementById('modReplyText').setAttribute('placeholder', placeholderText);

        //댓글번호 rno 가져오기 -
        const rno = e.target.closest('#replyContent').dataset.replyId;

        // 모달에 클릭한 댓글번호 달아놓기
        document.querySelector('.modal').dataset.rno = rno;
        });

    //수정요청 class : btn-dark
    document.getElementById('replyModBtn').addEventListener('click', e=> {

        fetchReplyModify();
    });

    async function fetchReplyModify() {

        const payload = {
            rno: document.querySelector('.modal').dataset.rno,
            newText: document.getElementById('modReplyText').value,
            bno: document.getElementById('wrap').dataset.bno
        }

        const res = await fetch(BASE_URL, {
            method: 'PUT',
            headers: {
                'content-type' : 'application/json'
            },
            body: JSON.stringify(payload)
        });

        if(!res.ok) {
            alert('수정 실패');
        } else {

            //수정 후 모달 닫기 버튼 클릭
            document.getElementById('modal-close').click();

            window.scrollTo(0,500); //수정 후 위로 이동
            await fetchInfScrollReplies();
        }
    }
}