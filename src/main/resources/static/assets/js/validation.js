

//회원가입 입력 검증 처리

//계정 중복검사 비동기 요청 보내기
// 중복 검사 비동기 요청 함수
async function fetchDuplicateCheck(type, keyword) {
    const resp = await fetch(`http://localhost:8181/members/check?type=${type}&keyword=${keyword}`);
    const flag = await resp.json();

    switch (type) {
        case 'account':
            idFlag = flag;
            break;
        case 'password':
            pwFlag = flag;
            break;
        case 'name':
            nameFlag = flag;
            break;
        case 'email':
            emailFlag = flag;
            break;
    }
    checkAllFieldsValid();
}

// 모든 필드의 유효성을 확인하고 버튼 활성화/비활성화
function checkAllFieldsValid() {
    if (idFlag === false && pwFlag === false && nameFlag === false && emailFlag === false &&
        $idInput.classList.contains('success') && $pwInput.classList.contains('success') &&
        $pw2Input.classList.contains('success') && $nameInput.classList.contains('success') &&
        $emailInput.classList.contains('success')) {
        $signUpBtn.disabled = false;
        $signUpBtn.style.backgroundColor = 'green';
    } else {
        $signUpBtn.disabled = true;
        $signUpBtn.style.backgroundColor = 'black';
    }
}

//계정 입력 검증
const $idInput = document.getElementById('user_id');
const $pwInput = document.getElementById('password');
const $pw2Input = document.getElementById('password_check');
const $nameInput = document.getElementById('user_name');
const $emailInput = document.getElementById('user_email');

// 검증 메세지를 위한 span
const $pwChk = document.getElementById('pwChk');
const $pw2Chk = document.getElementById('pwChk2');
const $nameChk = document.getElementById('nameChk');
const $emailChk = document.getElementById('emailChk');
const $signUpBtn = document.getElementById('signup-btn');

let idFlag = false;
let pwFlag = false;
let nameFlag = false;
let emailFlag = false;

// const pw2Value = $pw2Input.value;
// console.log(pw2Value);
$idInput.addEventListener('keyup', async (e) => {

    // 아이디 검사 정규표현식
    const accountPattern = /^[a-zA-Z0-9]{4,14}$/;

    //입력값 읽어오기
    const idValue = $idInput.value;
    // console.log(idValue);

    //검증 메시지를 입력할 span
    const $idChk = document.getElementById('idChk');

    //한글자 한글자 유효성 확인 : 정규표현식 패턴에 따른 유효성 검사
    if(idValue.trim() ==='') {
        // console.log('아이디는 필수!')
        $idInput.style.borderColor = 'red';
        $idChk.innerHTML = '<b class="warning">아이디는 필수 값입니다.</b>'
    } else if(!accountPattern.test(idValue)) {
        // console.log('아이디는 영문의 4 ~ 14글자 사이 ');
        $idInput.style.borderColor = 'red';
        $idChk.innerHTML = '<b class="warning">아이디는 영문의 4 ~ 14글자 사이, 영문 숫자로 입력해주세요</b>'
    } else {
        // console.log('정상입력 ! ');
        //아이디 중복검사 : 비동기 코드라 await으로 순서설정해서 기다리라는 명령을 줘야 한다.
        await fetchDuplicateCheck('account', idValue);

        if (idFlag) {
            $idInput.style.borderColor = 'red';
            $idChk.innerHTML = '<b class="warning">중복된 아이디 입니다. 다른 아이디를 사용해 주세요</b>'
        } else {
            $idInput.style.borderColor = 'green';
            $idChk.innerHTML = '<b class="success">짝짝짝 드디어 정상입력 ㅎㅎ </b>'
        }
    }
    checkAllFieldsValid();
})


// 비밀번호 입력 필드에서 스페이스바 입력 방지
$pwInput.addEventListener('keydown', (e) => {
    if (e.code === 'Space') {
        e.preventDefault();
    }
});

// 비밀번호 확인 입력 필드에서도 스페이스바 입력 방지
$pw2Input.addEventListener('keydown', (e) => {
    if (e.code === 'Space') {
        e.preventDefault();
    }
});



// 비밀번호 유효성 검사 함수
function validatePassword() {
    // 패스워드 검사 정규표현식 : 알파벳, 숫자, 특수문자가 포함된 8~20자 사이
    const passwordPattern = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!,@,#,$,%,^,&,*,?,_,~]).{8,20}$/;

    // 입력값 읽어오기
    let pwValue = $pwInput.value;

    // 스페이스바 공백 제거
    pwValue = pwValue.replace(/\s+/g, '');

    if (pwValue.trim() === '') {
        console.log('비밀번호 필수!');
        $pwInput.style.borderColor = 'red';
        $pwChk.innerHTML = '<b class="warning">비밀번호는 필수 값입니다.</b>';
        return false;
    } else if (!passwordPattern.test(pwValue)) {
        console.log('비밀번호는 8 ~ 20글자 사이');
        $pwInput.style.borderColor = 'red';
        $pwChk.innerHTML = '<b class="warning">비밀번호는 8글자 이상 20글자 이하이며, 알파벳, 숫자, 특수문자를 포함해야 합니다.</b>';
        return false;
    } else {
        console.log('정상입력!');
        $pwInput.style.borderColor = 'green';
        $pwChk.innerHTML = '<b class="success">유효한 비밀번호입니다.</b>';
        return true;
    }

    checkAllFieldsValid();
}

// 비밀번호 확인 유효성 검사 함수
function validatePasswordConfirm() {
    const pwValue = $pwInput.value.replace(/\s+/g, ''); // 스페이스바 공백 제거
    const pw2Value = $pw2Input.value.replace(/\s+/g, ''); // 스페이스바 공백 제거


    if (pw2Value.trim() === '') {
        $pw2Input.style.borderColor = 'red';
        $pw2Chk.innerHTML = '<b class="warning">비밀번호 확인은 필수 값입니다.</b>';
    } else if (pw2Value !== pwValue) {
        $pw2Input.style.borderColor = 'red';
        $pw2Chk.innerHTML = '<b class="warning">비밀번호가 일치하지 않습니다.</b>';
    } else {
        $pw2Input.style.borderColor = 'green';
        $pw2Chk.innerHTML = '<b class="success">비밀번호가 일치합니다.</b>';
    }
    checkAllFieldsValid();
}

// 비밀번호 입력 필드 이벤트 리스너
$pwInput.addEventListener('keyup', () => {
    validatePassword();
    validatePasswordConfirm(); // 비밀번호가 변경될 때마다 비밀번호 확인 필드의 유효성을 재검사
});

// 비밀번호 확인 입력 필드 이벤트 리스너
$pw2Input.addEventListener('keyup', validatePasswordConfirm);

// 이름 입력 검증
$nameInput.addEventListener('keyup', async (e) => {
    const namePattern = /^[가-힣]+$/;
    const nameValue = $nameInput.value.trim();

    if (nameValue === '') {
        $nameInput.style.borderColor = 'red';
        $nameChk.innerHTML = '<b class="warning">이름은 필수 값입니다.</b>';
    } else if (!namePattern.test(nameValue)) {
        $nameInput.style.borderColor = 'red';
        $nameChk.innerHTML = '<b class="warning">이름은 한글로 입력해주세요.</b>';
    } else {
        // await fetchDuplicateCheck('name', nameValue);

        // if (nameFlag) {
        //     $nameInput.style.borderColor = 'red';
        //     $nameChk.innerHTML = '<b class="warning">중복된 이름입니다. 다른 이름을 사용해 주세요.</b>';
        // } else {
            $nameInput.style.borderColor = 'green';
            $nameChk.innerHTML = '<b class="success"> 좋은 이름 이군용</b>';
        // }
    }
    checkAllFieldsValid();
});


$emailInput.addEventListener('keyup', async (e) => {

    // 이메일 검사 정규표현식
    const emailPattern = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

    const emailValue = $emailInput.value;

    if (emailValue.trim() === '') {
        $emailInput.style.borderColor = 'red';
        $emailChk.innerHTML = '<b class="warning">이메일은 필수 값입니다.</b>'
    } else if (!emailPattern.test(emailValue)) {
        $emailInput.style.borderColor = 'red';
        $emailChk.innerHTML = '<b class="warning">abc@gfdg.com의 바른 이메일 형식을 입력해 주세요</b>'
    } else {
        await fetchDuplicateCheck('email', emailValue);

        if (emailFlag) {
            $emailInput.style.borderColor = 'red';
            $emailChk.innerHTML = '<b class="warning">중복된 이메일 입니다. 다른 이메일 주소를 사용해 주세요</b>'
        } else {
            $emailInput.style.borderColor = 'green';
            $emailChk.innerHTML = '<b class="success">유효한 이메일 입니다. </b>'
        }
    }

    checkAllFieldsValid();
});

//모든 입력값이 존재하고 class가 success 이여야만 회원가입 버튼 기능 활성화

/*
$pwInput.addEventListener('keyup', async (e) => {

    // 패스워드 검사 정규표현식 : 알파벳이나 숫자가 포함된 문자열에 하나 이상의 특수문자가 포함되어 있는지를 확인
    const passwordPattern = /([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/;

    //입력값 읽어오기
    const pwValue = $pwInput.value;
    console.log(pwValue);

    //검증 메세지를 위한 span
    const $pwChk = document.getElementById('pwChk');
    //한글자 한글자 유효성 확인 : 정규표현식 패턴에 따른 유효성 검사
    //1. 알파벳 포함

    //2. 숫자 포함

    //3. 특수문자포함
    if(pwValue.trim() ==='') {
        console.log('비밀번호 필수!')
        $pwInput.style.borderColor = 'red';
        $pwChk.innerHTML = '<b class="warning">비밀번호는 필수 값입니다.</b>'
    } else if(!passwordPattern.test(pwValue)) {
        console.log('비밀번호는 8 ~ 20글자 사이 ');
        $pwInput.style.borderColor = 'red';
        $pwChk.innerHTML = '<b class="warning">비밀번호는 8글자 이상 20글자 이하이며, 알파벳, 숫자, 특수문자를 포함해야 합니다.</b>';
    } else {
        console.log('정상입력 ! ');
        //비밀번호 중복검사 : 비동기 코드라 await으로 순서설정해서 기다리라는 명령을 줘야 한다.
        await fetchDuplicateCheck(pwValue);
        if(pwFlag) {
                $pwInput.style.borderColor = 'green';
                $pwChk.innerHTML = '<b class="success">유효한 비밀번호니다. </b>'
            }
        }
})
$pw2Input.addEventListener('keyup', (e) => {
    // 입력값 읽어오기
    const pw2Value = $pw2Input.value;
    const pwValue = $pwInput.value;

    // 검증 메세지를 위한 span
    const $pw2Chk = document.getElementById('pwChk2');

    if (pw2Value.trim() === '') {
        $pw2Input.style.borderColor = 'red';
        $pw2Chk.innerHTML = '<b class="warning">비밀번호 확인은 필수 값입니다.</b>';
    } else if (pw2Value !== pwValue) {
        $pw2Input.style.borderColor = 'red';
        $pw2Chk.innerHTML = '<b class="warning">비밀번호가 일치하지 않습니다.</b>';
    } else {
        $pw2Input.style.borderColor = 'green';
        $pw2Chk.innerHTML = '<b class="success">비밀번호가 일치합니다.</b>';
    }
});


 */



