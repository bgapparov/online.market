$(document).ready(function () {
    function setUpCsrfToken(){
        let token = $("input[name='_csrf']").val();
        let header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    }
    $(".deleteAddress").click(function () {
        setUpCsrfToken();
        let addressId = $(this).data("addressId");
        $.ajax("/buyer/address/delete/" + addressId,
            {type: "DELETE"})
            .done(function (result) {
                location.reload();
            }).fail(function (jqXHR, textStatus) {
            alert("Request failed: " + textStatus);
        });
    });
    $(".deleteBilling").click(function () {
        setUpCsrfToken();
        let billingId = $(this).data("billingId");
        $.ajax("/buyer/billing/delete/" + billingId,
            {type: "DELETE"})
            .done(function (result) {
                location.reload();
            }).fail(function (jqXHR, textStatus) {
            alert("Request failed: " + textStatus);
        });
    });
});