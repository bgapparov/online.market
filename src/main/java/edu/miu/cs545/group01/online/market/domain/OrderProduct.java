package edu.miu.cs545.group01.online.market.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "orderProduct")
public class OrderProduct {

    @Id
    private long Id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    private float pointPayment;

    private float cashPayment;

    private float totalPayment;

    public OrderProduct() {
    }

    public OrderProduct(Order order, Product product, float pointPayment, float cashPayment, float totalPayment) {
        this.order = order;
        this.product = product;
        this.pointPayment = pointPayment;
        this.cashPayment = cashPayment;
        this.totalPayment = totalPayment;
    }

    public long getId() {
        return Id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getPointPayment() {
        return pointPayment;
    }

    public void setPointPayment(float pointPayment) {
        this.pointPayment = pointPayment;
    }

    public float getCashPayment() {
        return cashPayment;
    }

    public void setCashPayment(float cashPayment) {
        this.cashPayment = cashPayment;
    }

    public float getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(float totalPayment) {
        this.totalPayment = totalPayment;
    }
}
