function setUpCsrfToken(){
    let token = $("input[name='_csrf']").val();
    let header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}