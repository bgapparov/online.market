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
public class BillingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private BillingType type;

    private long cardNo;

    private  String cardName;

    private Date expirationDate;

    private String securityNumber;

    private String accountName;

    private long accountNumber;

    private long routingNumber;

    @ManyToOne
    private Address billingAddress;

    private BillingInfoStatus status;

    public BillingInfo() {
    }

    public BillingInfo(long cardNo, String cardName, Date expirationDate, String securityNumber, Address billingAddress, BillingInfoStatus status) {
        this.type = BillingType.CREDITCARD;
        this.cardNo = cardNo;
        this.cardName = cardName;
        this.expirationDate = expirationDate;
        this.securityNumber = securityNumber;
        this.billingAddress = billingAddress;
        this.status = status;
    }

    public BillingInfo( String accountName, long accountNumber, long routingNumber, Address billingAddress, BillingInfoStatus status) {
        this.type = BillingType.BANKACCOUNT;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.routingNumber = routingNumber;
        this.billingAddress = billingAddress;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public BillingType getType() {
        return type;
    }

    public void setType(BillingType type) {
        this.type = type;
    }

    public long getCardNo() {
        return cardNo;
    }

    public void setCardNo(long cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getSecurityNumber() {
        return securityNumber;
    }

    public void setSecurityNumber(String securityNumber) {
        this.securityNumber = securityNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(long routingNumber) {
        this.routingNumber = routingNumber;
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

    @Override
    public String toString() {
        return cardNo +
                ", " + cardName +
                ", " + expirationDate +
                ", " + securityNumber +
                ", " + accountName +
                ", " + accountNumber +
                ", " + routingNumber;
    }
}
