

// 유효성 검증 함수들을 import
import { validateInput } from './validate.js';
import {debounce} from "./util.js";

// 폼과 회원가입 버튼 요소를 가져옴
const form = document.getElementById('signUpForm');
const signupButton = document.getElementById('signup-btn');
const $password = document.getElementById('password');
const $password_check = document.getElementById('password_check');

// 각 필드에 대한 정보 배열 (id, 유효성 검증 함수, 에러 메시지 표시 요소, 초기 유효 상태)
const fields = [
    { id: 'user_id', validator: validateInput.account, errorElement: 'idChk', valid: false },
    { id: 'password', validator: validateInput.password, errorElement: 'pwChk', valid: false },
    { id: 'password_check', validator: (value) => validateInput.passwordCheck(value, document.getElementById('password').value), errorElement: 'pwChk2', valid: false },
    { id: 'user_name', validator: validateInput.name, errorElement: 'nameChk', valid: false },
    { id: 'user_email', validator: validateInput.email, errorElement: 'emailChk', valid: false }
];

// 버튼 상태를 업데이트하는 함수
const updateButtonState = () => {
    const isFormValid = fields.every(field => field.valid);
    if (isFormValid) {
        signupButton.disabled = false;
        signupButton.style.backgroundColor = 'orangered'; // 활성화된 버튼 배경색
    } else {
        signupButton.disabled = true;
        signupButton.style.backgroundColor = 'lightgray'; // 비활성화된 버튼 배경색
    }
};

// 각 필드에 대해 입력값 검증 이벤트 리스너를 추가
fields.forEach(field => {
    const $input = document.getElementById(field.id); // 입력 요소 가져오기
    $input.addEventListener('keyup', debounce(async (e) => { // 키보드 입력 시마다 유효성 검증
        const isValid = await field.validator($input.value); // 유효성 검증 함수 호출
        const $errorSpan = document.getElementById(field.errorElement); // 에러 메시지 표시 요소 가져오기
        console.log(isValid);
        if (isValid.valid) { // 유효한 경우
            $input.style.borderColor = 'skyblue'; // 입력 요소의 테두리 색 변경
            $errorSpan.innerHTML = '<b class="success">[사용가능합니다.]</b>'; // 성공 메시지 표시
            field.valid = true; // 필드의 유효 상태를 true로 설정
        } else { // 유효하지 않은 경우
            $input.style.borderColor = 'red'; // 입력 요소의 테두리 색 변경
            $errorSpan.innerHTML = `<b class="warning">[${isValid.message}]</b>`; // 에러 메시지 표시
            field.valid = false; // 필드의 유효 상태를 false로 설정
        }
        updateButtonState(); // 각 입력 유효성 검사 후 버튼 상태 업데이트
    }, 500));
});

// 비밀번호 유효성 검사 재확인 이벤트
$password.addEventListener('keyup', async (e) => {
    const value = $password_check.value; // 비밀번호 확인 필드의 값 가져오기
    const passwordValue = $password.value; // 비밀번호 필드의 값 가져오기

    const isValidPasswordCheck = await validateInput.passwordCheck(value, passwordValue);
    const $pwCheckErrorSpan = document.getElementById('pwChk2'); // 비밀번호 확인 에러 메시지 표시 요소 가져오기

    if (isValidPasswordCheck.valid) { // 유효한 경우
        $password_check.style.borderColor = 'skyblue'; // 입력 요소의 테두리 색 변경
        $pwCheckErrorSpan.innerHTML = '<b class="success">[일치합니다.]</b>'; // 성공 메시지 표시
        fields[2].valid = true; // 필드의 유효 상태를 true로 설정 (비밀번호 확인 필드)
    } else { // 유효하지 않은 경우
        $password_check.style.borderColor = 'red'; // 입력 요소의 테두리 색 변경
        $pwCheckErrorSpan.innerHTML = `<b class="warning">[${isValidPasswordCheck.message}]</b>`; // 에러 메시지 표시
        fields[2].valid = false; // 필드의 유효 상태를 false로 설정 (비밀번호 확인 필드)
    }
    updateButtonState(); // 각 입력 유효성 검사 후 버튼 상태 업데이트
});




// 회원가입 버튼 클릭 이벤트 리스너 추가
signupButton.addEventListener('click', (e) => {
    // 모든 필드가 유효한지 확인
    const isFormValid = fields.every(result => result.valid);

    if (isFormValid) { // 모든 필드가 유효한 경우
        form.submit(); // 폼 제출
    } else { // 유효하지 않은 필드가 있는 경우
        alert('입력란을 다시 확인하세요!'); // 경고 메시지 표시
    }
});

// 페이지 로드 시 초기 버튼 상태 업데이트
updateButtonState();


/*

// 유효성 검증 함수들을 import
import { validateInput } from './validate.js';
import { debounce } from './util.js';

// 폼과 회원가입 버튼 요소를 가져옴
const form = document.getElementById('signUpForm');
const signupButton = document.getElementById('signup-btn');


// 각 필드에 대한 정보 배열 (id, 유효성 검증 함수, 에러 메시지 표시 요소, 초기 유효 상태)
const fields = [
  { id: 'user_id', validator: validateInput.account, errorElement: 'idChk', valid: false },
  { id: 'password', validator: validateInput.password, errorElement: 'pwChk', valid: false },
  { id: 'password_check', validator: (value) => validateInput.passwordCheck(value, document.getElementById('password').value), errorElement: 'pwChk2', valid: false },
  { id: 'user_name', validator: validateInput.name, errorElement: 'nameChk', valid: false },
  { id: 'user_email', validator: validateInput.email, errorElement: 'emailChk', valid: false }
];

// 버튼 상태를 업데이트하는 함수
const updateButtonState = () => {

  // 모든 valid가 true인지 확인
  const isFormValid = fields.every(field => field.valid);

  if (isFormValid) {
    signupButton.disabled = false;
    signupButton.style.backgroundColor = 'orangered'; // 활성화된 버튼 배경색
  } else {
    signupButton.disabled = true;
    signupButton.style.backgroundColor = 'lightgray'; // 비활성화된 버튼 배경색
  }
};

// 입력 필드의 유효성을 검사하고 에러 메시지를 업데이트하는 함수
const validateField = async (field) => {
  const $input = document.getElementById(field.id);
  const $errorSpan = document.getElementById(field.errorElement);
  const isValid = await field.validator($input.value);
  console.log(isValid);

  if (isValid.valid) {
    $input.style.borderColor = 'skyblue';
    $errorSpan.innerHTML = '<b class="success">[사용가능합니다.]</b>';
    field.valid = true;
  } else {
    $input.style.borderColor = 'red';
    $errorSpan.innerHTML = `<b class="warning">[${isValid.message}]</b>`;
    field.valid = false;
  }
  updateButtonState();
};


// 각 필드에 이벤트 리스너 추가
fields.forEach(field => {
  const $input = document.getElementById(field.id);
  $input.addEventListener('keyup', debounce(() => {
    validateField(field);
    // 비밀번호 필드가 변경될 때 비밀번호 확인 필드의 유효성도 다시 검사
    if (field.id === 'password') {
      const passwordCheckField = fields.find(f => f.id === 'password_check');
      validateField(passwordCheckField);
    }
  }, 500));
});

// 회원가입 버튼 클릭 이벤트 리스너 추가
signupButton.addEventListener('click', (e) => {
  // 모든 필드가 유효한지 확인
  const isFormValid = fields.every(result => result.valid);

  if (isFormValid) { // 모든 필드가 유효한 경우
    form.submit(); // 폼 제출
  } else { // 유효하지 않은 필드가 있는 경우
    alert('입력란을 다시 확인하세요!'); // 경고 메시지 표시
  }
});


// 페이지 로드 시 초기 버튼 상태 업데이트
updateButtonState();

 */