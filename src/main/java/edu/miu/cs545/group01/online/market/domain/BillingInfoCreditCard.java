package edu.miu.cs545.group01.online.market.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@DiscriminatorValue("CreditCard")
public class BillingInfoCreditCard extends BillingInfo {

    @NotBlank(message = "{card.cardNo}")
    private String cardNo;
    @NotBlank(message = "{card.cardName}")
    private  String cardName;
    @NotNull(message = "{card.expirationDate}")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date expirationDate;
    @NotBlank(message = "{card.securityNumber}")
    private String securityNumber;

    public BillingInfoCreditCard() {
    }

    @Override
    public String getType() {
        return "card";
    }

    public BillingInfoCreditCard(Buyer buyer, String cardNo, String cardName, Date expirationDate, String securityNumber, Address billingAddress) {
        super(buyer, billingAddress);

        this.cardNo = cardNo;
        this.cardName = cardName;
        this.expirationDate = expirationDate;
        this.securityNumber = securityNumber;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
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
