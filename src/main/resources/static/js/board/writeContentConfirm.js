function writeContentConfirm() {
    console.log('writeContent() CALLED!!');

    let form = document.writeContentForm;
    if (form.title.value == '') {
        alert('제목은 필수입력 사항입니다.\n제목을 입력해주세요.');
        form.title.focus();

    } else {
        form.submit();

    }

}