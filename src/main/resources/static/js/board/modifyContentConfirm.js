function modifyContentConfirm() {
    console.log('modifyContentConfirm() CALLED!!');

    let form = document.modifyContentForm;
    if (form.title.value == '') {
        alert('제목은 필수입력 사항입니다.\n제목을 입력해주세요.');
        form.title.focus();

    } else {
        form.submit();

    }
}

function deleteContentConfirm() {
    console.log('deleteContentConfirm() CALLED!!')

    let form = document.modifyContentForm || document.detailContentForm;
    if(confirm('정말로 게시물을 삭제하시겠습니까?')){
        form.action = '/board/deleteContentConfirm'
        form.method = 'get'
        form.submit();
    }
}