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
    $(".followSeller").click(function () {
        setUpCsrfToken();
        let sellerId = $(this).data("sellerId");
        $.ajax("/buyer/follow-seller/" + sellerId,
            {type: "PUT"})
            .done(function (result) {
                location.reload();
            }).fail(function (jqXHR, textStatus) {
            alert("Request failed: " + textStatus);
        });
    });
    $(".unfollowSeller").click(function () {
        setUpCsrfToken();
        let sellerId = $(this).data("sellerId");
        $.ajax("/buyer/unfollow-seller/" + sellerId,
            {type: "PUT"})
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
    $(".remove-shopping-cart").click(function () {
        setUpCsrfToken();
        let cartId = $(this).data("cartId");
        $.ajax("/buyer/cart/delete/" + cartId,
            {type: "DELETE"})
            .done(function (result) {
                location.reload();
            }).fail(function (jqXHR, textStatus) {
            alert("Request failed: " + textStatus);
        });
    });
    $(".addCart").click(function () {
        setUpCsrfToken();
        let productId = $(this).data("productId");
        $.ajax("/buyer/cart/add/" + productId,
            {type: "POST"})
            .done(function (result) {
                location.reload();
            }).fail(function (jqXHR, textStatus) {
            alert("Request failed: " + textStatus);
        });
    });
    $(".checkoutQuantity").change(function () {
        setUpCsrfToken();
        let cartId = $(this).data("cartId");
        let quantity = $(this).val();
        $.post("/buyer/cart/set-quantity/" + cartId + "/"+quantity)
            .done(function (result) {
                location.reload();
            }).fail(function (jqXHR, textStatus) {
            alert("Request failed: " + textStatus);
        });
    });
    $(".addFollow").change(function () {
        setUpCsrfToken();
        let sellerId = $(this).data("sellerId");
        let quantity = $(this).val();
        $.post("/buyer/follow-seller/" + sellerId)
            .done(function (result) {
                location.reload();
            }).fail(function (jqXHR, textStatus) {
            alert("Request failed: " + textStatus);
        });
    });
});