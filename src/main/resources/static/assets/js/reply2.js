//=================== 전역 변수 ============================

const BASE_URL = 'http://localhost:8181/api/v1/replies';
const bno = document.getElementById('wrap').dataset.bno; //게시물 글번호
console.log('글번호 : ', bno);

//=================== 함수 정의 ============================

async function fetchGet() {
    //await : then 메서드를 대신 호출해서 promise result를 바로 가져옴 ~ 바로 response 배열 튀나옴
    const result = await fetch(BASE_URL/`${bno}`);
    console.log(result);

    const json = await result.json();
    console.log(json);
}

async function renderReplies(replies) {

    //댓글 수 렌더링
    console.log(replies.length);
    document.getElementById('replyCnt').textContent = replies.length;

    //댓글 목록 렌더링
    let tag = ``;
    if (replies && replies.length > 0) {
        replies.forEach( ({createAt, reply_no: rno, writer, text}) => {
            tag += `
        <div id='replyContent' class='card-body' data-reply-id='${rno}'>
        <div class='row user-block'>
            <span class='col-md-3'>
                <b>${writer}</b>
            </span>
            <span class='offset-md-6 col-md-3 text-right'><b>${createAt}</b></span>
        </div><br>
        <div class='row'>
            <div class='col-md-9'>${text}</div>
            <div class='col-md-3 text-right'>
                <a id='replyModBtn' class='btn btn-sm btn-outline-dark' data-bs-toggle='modal' data-bs-target='#replyModifyModal'>수정</a>&nbsp;
                <a id='replyDelBtn' class='btn btn-sm btn-outline-dark' href='#'>삭제</a>
            </div>
        </div>
    </div>
    `;
        })
    } else {
        tag = `<div id='replyContent' class='card-body'>댓글이 아직 없습니다! ㅠㅠ</div>`;
    }
    document.getElementById('replyData').innerHTML = tag;

}

async function fetchReplies() {
    const resp = await  fetch(`${BASE_URL}/${bno}`);
    const replies = await resp.json();

    //댓글목록 렌더링
    renderReplies(replies);
    // console.log(replies);
}

function postReplies() {
    // 등록 버튼을 누르면 입력한 값을 서버에 전송
    document.getElementById('replyAddBtn').onclick = e => {
        console.log("버튼을 클릭함");

            const requestInfo = {
                method:'POST',
                headers:{ //요청헤더정보르 키밸류 페어로 적어놓아야함. JSON임을 명시한다.
                    'content-type' : 'application/json'
                },
                body: JSON.stringify ({ //받을 데이터를 명시한다. (java 코드 )
                    text: document.getElementById('newReplyText').value , //유사JSON이니까 stringify로 언어 바꿔주기
                    author: document.getElementById('newReplyWriter').value ,
                    bno: bno
                })
            };

            //requestinfo 전달해야함
        fetch(BASE_URL, requestInfo) //두번째 파라미터는 위와 같이 api 형식이 정해져있다
            .then(res => res.json()) //갱신된 댓글 목록 서버코드에서 가져오기
            .then(json => {
                console.log(json);

                fetchGet();
        });
    };


}

//=================== 실행  코드 ============================

//댓글 목록 서버에서 불러오기
fetchReplies();

//입력한 댓글 POST로 가져오기
postReplies();







