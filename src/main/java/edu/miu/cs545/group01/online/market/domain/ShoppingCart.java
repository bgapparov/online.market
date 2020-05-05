package edu.miu.cs545.group01.online.market.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class ShoppingCart {

    @Id
    private long Id;

    @ManyToOne
    private Buyer buyer;

    @ManyToOne
    private Product product;

    private int quantity;

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
