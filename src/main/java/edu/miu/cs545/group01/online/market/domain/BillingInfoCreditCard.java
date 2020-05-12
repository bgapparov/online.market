package edu.miu.cs545.group01.online.market.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("CreditCard")
public class BillingInfoCreditCard extends BillingInfo {

    private long cardNo;

    private  String cardName;

    private Date expirationDate;

    private String securityNumber;

    public BillingInfoCreditCard() {
    }

    @Override
    public String getType() {
        return "card";
    }

    public BillingInfoCreditCard(Buyer buyer, long cardNo, String cardName, Date expirationDate, String securityNumber, Address billingAddress) {
        super(buyer, billingAddress);

        this.cardNo = cardNo;
        this.cardName = cardName;
        this.expirationDate = expirationDate;
        this.securityNumber = securityNumber;
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

    @Override
    public String toString() {
        return "Credit card:" + cardNo +
                ", " + cardName +
                ", " + expirationDate +
                ", " + securityNumber ;
    }
}
