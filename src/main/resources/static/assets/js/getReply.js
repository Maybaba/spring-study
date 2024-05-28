
import {BASE_URL} from "./reply.js";

//날짜 관련 포매팅하기
function getRelativeTime(createAt) {
    //현재시간 구하기 (밀리초)
    const now = new Date();
    // console.log(now);
    const past = new Date(createAt);

    //몇시간 전
    const diff = now - past;
    // console.log(diff);

    const seconds = Math.floor(diff / 1000);
    const minutes = Math.floor(diff / 1000 / 60);
    const hours = Math.floor(diff / 1000 / 60 / 60);
    const days = Math.floor(diff / 1000 / 60 / 60 / 24);
    const weeks = Math.floor(diff / 1000 / 60 / 60 / 24 / 7);
    const years = Math.floor(diff / 1000 / 60 / 60 / 24 / 365);

    if (seconds < 60) {
        return '방금 전';
    } else if (minutes < 60) {
        return `${minutes}분 전`;
    } else if (hours < 24) {
        return `${hours}시간 전`;
    } else if (days < 7) {
        return `${days}일 전`;
    } else if (weeks < 52) {
        return `${weeks}주 전`;
    } else {
        return `${years}년 전`;
    }
}

/*
// 디스트럭쳐링으로 배열객체 안의 키밸류 뿌셔~!!! pageinfo -> 키 하나하나하나하나
function renderPage({ begin, end, pageInfo, prev, next }) {
    let tag = '';

    // prev 만들기
    if (prev) tag += `<li class='page-item'><a class='page-link page-active' href='${begin - 1}'>이전</a></li>`;

    // 페이지 번호 태그 만들기
    for (let i = begin; i <= end; i++) {

        let active = '';
        if (pageInfo.pageNo === i) active = 'p-active';

        tag += `
      <li class='page-item ${active}'>
        <a class='page-link page-custom' href='${i}'>${i}</a>
      </li>`;
    }

    // next 만들기
    if (next) tag += `<li class='page-item'><a class='page-link page-active' href='${end + 1}'>다음</a></li>`;

    // 페이지 태그 ul에 붙이기
    const $pageUl = document.querySelector('.pagination');
    $pageUl.innerHTML = tag;
}

 */

export function renderReplies({pageInfo, replies}) { //기존 replies 디스트럭쳐링

    // 댓글 수 렌더링
    document.getElementById('replyCnt').textContent = pageInfo.totalCount;

    // 댓글 목록 렌더링
    let tag = '';
    if (replies && replies.length > 0) {
        replies.forEach(({ reply_no: rno, writer, text, createAt }) => {
            tag += `
        <div id='replyContent' class='card-body' data-reply-id='${rno}'>
            <div class='row user-block'>
                <span class='col-md-3'>
                    <b>${writer}</b>
                </span>
                <span class='offset-md-6 col-md-3 text-right'><b>${getRelativeTime(createAt)}</b></span>
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
        });


    } else {
        tag = `<div id='replyContent' class='card-body'>댓글이 아직 없습니다!유유</div>`;
    }

    document.getElementById('replyData').innerHTML = tag;

    //페이지 태그 렌더링
    // renderPage(pageInfo);
}

// 서버에서 댓글 목록 가져오는 비동기 요청 함수, 초반
export async function fetchReplies(pageNo=1) {

    const bno = document.getElementById('wrap').dataset.bno; // 게시물 글번호

    const res = await fetch(`${BASE_URL}/${bno}/page/${pageNo}`);
    const replyResponse = await res.json();
    // {  replies : [ {} {} {} ]  }

    console.log('초기댓글비동기요청함수:',pageNo); //잘 가져와 짐 처음 설정했으니까

    // 댓글 목록 렌더링
    renderReplies(replyResponse);
}
//무한스크롤 함수
export function InfiniteScroll(pageInfo) {
    // 스크롤 이벤트 감지 및 무한 스크롤 로딩
    window.addEventListener('scroll', () => {
        if (window.innerHeight + window.scrollY >= document.body.offsetHeight - 100) {
            // 현재 페이지 번호 가져와서 다음 페이지 번호로 할당
            const pageNo = pageInfo.pageNo + 1;
            // 다음 페이지의 댓글 가져오기
            fetchReplies(pageNo);
        }
    });
}



/*
//페이징 버튼 클릭 이벤트 생성 함수 : 클릭하면 비동기로 페치처리 할 수 있도록 처리
export function replyPageClickEvent(e) {

    document.querySelector('.pagination').addEventListener('click', e => {
        e.preventDefault();
        // getAttribute로 속성값 가져오기
        const $thisPage = e.target.getAttribute('href');
        //선택한 페이지 값을 재랜더링하기
        fetchReplies($thisPage);

        //비동기코드이므로 순서 상관 없다잉?... 이해가 잘 안간다. -> 기능을 구현하는데에 있어서 모든 기능을 한번에 넣지 말라는 뜻

        //현재페이지 - href값 받아온 후 그 값의 부모 태그 잡아서 p-active 클래스 추가하기 :
        // const $parentElement = e.target.parentElement;
        // console.log('$parentElement : ',e.target);
        // $parentElement.classList.add('p-active');
        // console.log($parentElement)
    });

 */



