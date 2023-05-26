$(function(){
    // TODO
    // 검증코드가 있어야 한다.
    $("#btnDel").click(function () {

        let answer = confirm("삭제하시겠습니까?");
        if (answer) {
            $("form[name='frmDelete']").submit();
        }

    });
});