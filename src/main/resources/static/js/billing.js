$(document).ready(function () {
    function setUpCsrfToken(){
        let token = $("input[name='_csrf']").val();
        let header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    }
    $(".deleteBilling").click(function () {
        setUpCsrfToken();
        let addressId = $(this).data("addressId");
        $.ajax("/buyer/billing/delete/" + addressId,
            {type: "DELETE"})
            .done(function (result) {
                location.reload();
            }).fail(function (jqXHR, textStatus) {
            alert("Request failed: " + textStatus);
        });
    });
});