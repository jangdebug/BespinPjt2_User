// 숫자를 3자리마다 쉼표가 있는 형식으로 변환하는 함수
function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

// 페이지 로드 후 숫자 포맷팅 적용
window.addEventListener('DOMContentLoaded', (event) => {
    const numberWithCommasEls = document.querySelectorAll('.price');
    numberWithCommasEls.forEach(el => {
        const originalNumber = parseFloat(el.textContent);
        if (!isNaN(originalNumber)) {
            el.textContent = numberWithCommas(originalNumber);
        }
    });
});