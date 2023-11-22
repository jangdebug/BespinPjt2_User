function registProductConfirm(){
    console.log("registProductConfirm()");

    let form = document.registProductForm;
    let isLiElement = $("#checkedProduct ul li");

    if(isLiElement.length < 1){
        alert('추가하려는 상품이 없습니다.');
    } else{
        alert('상품 등록을 완료하였습니다.');
        form.submit();
    }
}