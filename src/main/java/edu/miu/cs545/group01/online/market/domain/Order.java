package edu.miu.cs545.group01.online.market.domain;

import edu.miu.cs545.group01.online.market.domain.enums.OrderStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity(name = "orderTable")
//@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull(message = "{order.orderDate}")
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    private Buyer buyer;

    @ManyToOne
    private Seller seller;

    @ManyToOne
    private Address shippingAddress;

    @ManyToOne
    private BillingInfo billingInfo;

    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date shippingDate;
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date deliveredDate;
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date canceledDate;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderProduct> orderedProducts;

    public Order() {
    }

    public Order(Date orderDate, OrderStatus status, Buyer buyer, Seller seller, Address shippingAddress, BillingInfo billingInfo, Date shippingDate, Date deliveredDate, Date canceledDate) {
        this.orderDate = orderDate;
        this.status = status;
        this.buyer = buyer;
        this.seller = seller;
        this.shippingAddress = shippingAddress;
        this.billingInfo = billingInfo;
        this.shippingDate = shippingDate;
        this.deliveredDate = deliveredDate;
        this.canceledDate = canceledDate;
    }

    public long getId() {
        return id;
    }

    public Date getCanceledDate() {
        return canceledDate;
    }

    public void setCanceledDate(Date canceledDate) {
        this.canceledDate = canceledDate;
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
    public boolean isStatusCreated(){
        return status == OrderStatus.CREATED;
    }
    public boolean isStatusDelivered(){
        return status == OrderStatus.DELIVERED;
    }

    public boolean isStatusShipped(){
        return status == OrderStatus.SHIPPED;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<OrderProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<OrderProduct> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public float getTotalSum(){
        float result= 0;
        for (OrderProduct orderProduct : orderedProducts) {
            result += orderProduct.getTotalPayment();
        }
        return  result;
    }
    public String getOrderedProductsText(){
        return orderedProducts.stream().map(o->o.toString()).reduce((op1, op2)->op1+"\r\n"+op2).orElse("");
//        String result = "";
//        for (OrderProduct orderProduct : orderedProducts) {
//            result += orderProduct +"\r\n";
//        }
//        return result;
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

