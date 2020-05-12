package edu.miu.cs545.group01.online.market.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(
        name = "billingType",
        discriminatorType=DiscriminatorType.STRING
)
public abstract class BillingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Buyer buyer;

    @ManyToOne
    private Address billingAddress;

    public BillingInfo() {
    }

    public BillingInfo(Buyer buyer, Address billingAddress) {
        this.buyer = buyer;
        this.billingAddress = billingAddress;
    }
    public long getId() {
        return id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public abstract String getType();
}
