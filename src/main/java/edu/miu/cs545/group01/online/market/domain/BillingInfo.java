package edu.miu.cs545.group01.online.market.domain;

import edu.miu.cs545.group01.online.market.domain.enums.BillingInfoStatus;
import edu.miu.cs545.group01.online.market.domain.enums.BillingType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(
        name="billingType",
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

    private BillingInfoStatus status;

    public BillingInfo() {
    }

    public BillingInfo(Buyer buyer, Address billingAddress, BillingInfoStatus status) {
        this.buyer = buyer;
        this.billingAddress = billingAddress;
        this.status = status;
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

    public BillingInfoStatus getStatus() {
        return status;
    }

    public void setStatus(BillingInfoStatus status) {
        this.status = status;
    }

}
