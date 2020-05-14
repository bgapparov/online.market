package edu.miu.cs545.group01.online.market.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Buyer buyer;

    @ManyToOne
    private Product product;
    @Min(value = 1, message = "{shoppingcart.quantity}")
    private int quantity;
    @NotNull(message = "{shoppingcart.date}")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date addDate;

    public ShoppingCart() {
    }

    public ShoppingCart(Buyer buyer, Product product, int quantity, Date addDate) {
        this.buyer = buyer;
        this.product = product;
        this.quantity = quantity;
        this.addDate = addDate;
    }

    public long getId() {
        return Id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
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

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
}
