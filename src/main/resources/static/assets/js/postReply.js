import {BASE_URL} from "./reply.js";
import {fetchInfScrollReplies} from "./getReply.js"

const textInput = document.getElementById('newReplyText');
const writerInput = document.getElementById('newReplyWriter');


export const fetchReplyPost = async () => {
    //서버에 댓글 등록을 요청하는 비동기 함수
    //화살표함수!
    const payload = {
        text: textInput.value,
        author: writerInput.value ,
        bno: document.getElementById('wrap').dataset.bno
    }
    console.log("post payload : ", payload);

    const resp = await fetch(`${BASE_URL}`, {
        method: 'POST',
        headers: {
            'content-type' : 'application/json'
        },
        body: JSON.stringify(payload)
    });

    if (resp.status=== 403) {
        alert("로그인이 필요한 서비스입니다");
        window.location.href = "/members/log-in";
        return;
    }



    const replies = await resp.json();
    console.log(replies);

    textInput.value = '';
    // writerInput.value = '';
    renderReplies(replies);
    fetchInfScrollReplies();
    window.scrollTo(0,0); //입력 끝나고 화면 상단으로 이동
}