package edu.miu.cs545.group01.online.market.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.*;

@Entity
public class Follows {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @ManyToOne
    private Seller seller;

    @ManyToOne
    private  Buyer buyer;

    public Follows() {
    }

    public Follows(Seller seller, Buyer buyer) {

        this.seller = seller;
        this.buyer = buyer;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
}
