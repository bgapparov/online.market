package edu.miu.cs545.group01.online.market.domain;

import edu.miu.cs545.group01.online.market.validator.PointValidation;

import javax.validation.constraints.NotNull;

public class CheckoutModel {
    @NotNull(message = "{checkout.shippingAddressId}")
    private Long shippingAddressId;
    @NotNull(message = "{checkout.billingInfoId}")
    private Long billingInfoId;
    @PointValidation(message = "{checkout.pointPayment}")
    private float pointPayment;

    private Buyer buyer;

    public Long getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(Long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public Long getBillingInfoId() {
        return billingInfoId;
    }

    public void setBillingInfoId(Long billingInfoId) {
        this.billingInfoId = billingInfoId;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public float getPointPayment() {
        return pointPayment;
    }

    public void setPointPayment(float pointPayment) {
        this.pointPayment = pointPayment;
    }
}
