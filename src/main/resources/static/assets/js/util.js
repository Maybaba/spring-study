        //
        //     const $search = document.getElementById('search');
        //     const $account = document.getElementById('account');
        //     const showResult = (value) => {
        //     console.log(`입력값: ${value}`);
        // };

            // 디바운싱 함수 정의 : 서버 공격 방지를 위한 보호
           export function debounce(callback, wait) {
            let timer;

            return (...value) => {
            clearTimeout(timer);
            timer = setTimeout(() => {
            callback(...value);
        }, wait);
        };

        }
        //     const debounceClosure1 = debounce(showResult, 500);
        //
        //     $search.addEventListener('keyup', e => {
        //     // 입력값 가져오기
        //     const value = $search.value;
        //
        //     debounceClosure1(value);
        // });
        //
        //     const debounceClosure2 = debounce(showResult, 1000);
        //
        //     $account.addEventListener('keyup', e => {
        //     // 입력값 가져오기
        //     const value = $account.value;
        //
        //     debounceClosure2(value);
        // });
