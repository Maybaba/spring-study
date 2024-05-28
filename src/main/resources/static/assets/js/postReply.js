import {BASE_URL} from "./reply.js";
import {renderReplies} from "./getReply.js"

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
    console.log(payload);

    const resp = await fetch(`${BASE_URL}`, {
        method: 'POST',
        headers: {
            'content-type' : 'application/json'
        },
        body: JSON.stringify(payload)
    });

    const replies = await resp.json();
    console.log(replies);

    // textInput = '';
    // writerInput = '';
    renderReplies(replies);
}