$(document).ready(function () {
    function setUpCsrfToken(){
        let token = $("input[name='_csrf']").val();
        let header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    }
    $(".approveSeller").click(function () {
        setUpCsrfToken();
        let sellerId = $(this).data("sellerId");
        $.ajax("/admin/approve-seller/" + sellerId,
            {type: "PUT"})
            .done(function (result) {
                location.reload();
            }).fail(function (jqXHR, textStatus) {
                alert("Request failed: " + textStatus);
            });
    });

    $(".rejectSeller").click(function () {
        setUpCsrfToken();
        let sellerId = $(this).data("sellerId");

        $.ajax("/admin/reject-seller/" + sellerId,
            {type: "PUT"})
            .done(function (result) {
                location.reload();
            }).fail(function (jqXHR, textStatus) {
                alert("Request failed: " + textStatus);
            });
    });

    $(".postReview").click(function () {
        setUpCsrfToken();
        let reviewId = $(this).data("reviewId");
        $.ajax("/admin/post-review/" + reviewId,
            {type: "PUT"})
            .done(function (result) {
                location.reload();
            }).fail(function (jqXHR, textStatus) {
            alert("Request failed: " + textStatus);
        });
    });

    $(".declineReview").click(function () {
        setUpCsrfToken();
        let reviewId = $(this).data("reviewId");
        $.ajax("/admin/decline-review/" + reviewId,
            {type: "PUT"})
            .done(function (result) {
                location.reload();
            }).fail(function (jqXHR, textStatus) {
            alert("Request failed: " + textStatus);
        });
    });
});