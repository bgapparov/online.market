$(document).ready(function () {
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

    $(".cancelOrderBySeller").click(function () {
        setUpCsrfToken();
        let orderId = $(this).data("orderId");
        $.ajax("/seller/order/cancel/" + orderId,
            {type: "DELETE"})
            .done(function (result) {
                location.reload();
            }).fail(function (jqXHR, textStatus) {
            alert("Request failed: " + textStatus);
        });
    });
    $(".makeAsShipped").click(function () {
        setUpCsrfToken();
        let orderId = $(this).data("orderId");
        $.ajax("/seller/order/shipped/" + orderId,
            {type: "PUT"})
            .done(function (result) {
                location.reload();
            }).fail(function (jqXHR, textStatus) {
            alert("Request failed: " + textStatus);
        });
    });
    $(".makeAsDelivered").click(function () {
        setUpCsrfToken();
        let orderId = $(this).data("orderId");
        $.ajax("/seller/order/delivered/" + orderId,
            {type: "PUT"})
            .done(function (result) {
                location.reload();
            }).fail(function (jqXHR, textStatus) {
            alert("Request failed: " + textStatus);
        });
    });
});