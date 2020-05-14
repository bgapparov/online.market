package edu.miu.cs545.group01.online.market.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity(name = "orderProduct")
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;
    @Min(value = 0, message = "{orderproduct.pointPayment}")
    private float pointPayment;
    @Min(value = 0, message = "{orderproduct.cashPayment}")
    private float cashPayment;
    @Min(value = 0, message = "{orderproduct.totalPayment}")
    private float totalPayment;
    @Min(value = 0, message = "{orderproduct.quantity}")
    private int quantity;

    @OneToOne(mappedBy = "orderProduct", fetch = FetchType.EAGER)
    private Review review;

    public OrderProduct() {
    }

    public OrderProduct(Order order, Product product, int quantity, float pointPayment) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.pointPayment = pointPayment;
        float cashPayment = product.getPrice()*quantity - pointPayment;
        this.cashPayment = cashPayment;
        this.totalPayment = pointPayment + cashPayment;
    }

    public long getId() {
        return Id;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
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

    public boolean needReview(){
        return review == null;
    }

    @Override
    public String toString() {
        return product + "; quantity: " + quantity;
    }
}
