$(document).ready(function () {

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

    $(".cancelOrder").click(function () {
        setUpCsrfToken();
        let orderId = $(this).data("orderId");
        $.ajax("/buyer/order/cancel/" + orderId,
            {type: "DELETE"})
            .done(function (result) {
                location.reload();
            }).fail(function (jqXHR, textStatus) {
            alert("Request failed: " + textStatus);
        });
    });
});