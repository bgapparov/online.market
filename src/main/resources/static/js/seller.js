$(document).ready(function () {
    function setUpCsrfToken(){
        let token = $("input[name='_csrf']").val();
        let header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    }
    $(".deleteProduct").click(function () {
        setUpCsrfToken();
        let productId = $(this).data("productId");
        $.ajax("/seller/product/remove/" + productId,
            {type: "DELETE"})
            .done(function (result) {
                location.reload();
            }).fail(function (jqXHR, textStatus) {
            alert("Request failed: " + textStatus);
        });
    });

});