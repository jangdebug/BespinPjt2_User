function loginConfirm() {
    console.log("loginConfirm() CALLED!!!");

    let form = document.loginForm;

    if(form.id.value == ''){
        alert("아이디를 입력해주세요!");
        form.id.focus();
    } else if(form.password.value == ''){
        alert("비밀번호를 입력해주세요!");
        form.password.focus();
    } else {
        form.submit();
    }

}