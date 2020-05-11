package edu.miu.cs545.group01.online.market.domain;

import edu.miu.cs545.group01.online.market.domain.enums.OrderStatus;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "orderTable")
//@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    private Date orderDate;

    private OrderStatus status;

    @ManyToOne
    private Buyer buyer;

    @ManyToOne
    private Seller seller;

    @ManyToOne
    private Address shippingAddress;

    @ManyToOne
    private BillingInfo billingInfo;

    private Date shippingDate;

    private Date deliveredDate;


    public Order() {
    }

    public Order(Date orderDate, OrderStatus status, Buyer buyer, Seller seller, Address shippingAddress, BillingInfo billingInfo, Date shippingDate, Date deliveredDate) {
        this.orderDate = orderDate;
        this.status = status;
        this.buyer = buyer;
        this.seller = seller;
        this.shippingAddress = shippingAddress;
        this.billingInfo = billingInfo;
        this.shippingDate = shippingDate;
        this.deliveredDate = deliveredDate;
    }

    public long getId() {
        return Id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Address getAddress() {
        return shippingAddress;
    }

    public void setAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public BillingInfo getBillingInfo() {
        return billingInfo;
    }

    public void setBillingInfo(BillingInfo billingInfo) {
        this.billingInfo = billingInfo;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public Date getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(Date deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

}

