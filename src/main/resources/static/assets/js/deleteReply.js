import { BASE_URL } from "./reply.js";
import { fetchInfScrollReplies, removeInfiniteScroll } from "./getReply.js";
import { callApi } from "./api.js";

// 댓글 삭제 비동기 요청 처리 함수
const fetchDeleteReply = async (rno) => {

    await callApi(`${BASE_URL}/${rno}`, 'DELETE');

    // const res = await fetch(`${BASE_URL}/${rno}`,{
    //     method: 'DELETE'
    // });
    //
    // if (res.status != 200) {
    //     alert('삭제에 실패했습니다 ㅜ ');
    //     return;
    // }

    fetchInfScrollReplies(); //삭제 후 댓글 페이지 재로딩하기
    window.scrollTo(0,0); // 삭제 후 페이지 상단으로 이동
}

//댓글 삭제 처리 이벤트 등록 함수
export function removeReplyClickEvent() {

    document.getElementById('replyData').addEventListener('click', e => {
       if (!e.target.matches('#replyDelBtn')) return;

       if(!confirm('정말 삭제할까요??')) return;

        // console.log('삭제 버튼 클릭!');
        const rno = e.target.closest('#replyContent').dataset.replyId;
        fetchDeleteReply(rno);
    })

}