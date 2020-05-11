package edu.miu.cs545.group01.online.market.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "orderProduct")
public class OrderProduct {

    @Id
    @GeneratedValue
    private long Id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;
    private float pointPayment;
    private float cashPayment;
    private float totalPayment;
    private int quantity;

    public OrderProduct() {
    }

    public OrderProduct(Order order, Product product, int quantity, float pointPayment, float cashPayment) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.pointPayment = pointPayment;
        this.cashPayment = cashPayment;
        this.totalPayment = pointPayment + cashPayment;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPointPayment() {
        return pointPayment;
    }

    public void setPointPayment(float pointPayment) {
        this.pointPayment = pointPayment;
        this.totalPayment = this.cashPayment + this.pointPayment;
    }

    public float getCashPayment() {
        return cashPayment;
    }

    public void setCashPayment(float cashPayment) {
        this.cashPayment = cashPayment;
        this.totalPayment = this.cashPayment + this.pointPayment;
    }

    public float getTotalPayment() {
        return totalPayment;
    }

    @Override
    public String toString() {
        return product + "; quantity: " + quantity;
    }
}
