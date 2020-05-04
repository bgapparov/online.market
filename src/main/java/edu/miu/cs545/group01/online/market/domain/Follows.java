package edu.miu.cs545.group01.online.market.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Follows {

    @Id
    private long Id;

    @ManyToOne
    private Seller seller;

    @ManyToOne
    private  Buyer buyer;

    public Follows() {
    }

    public Follows(long id, Seller seller, Buyer buyer) {
        Id = id;
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
