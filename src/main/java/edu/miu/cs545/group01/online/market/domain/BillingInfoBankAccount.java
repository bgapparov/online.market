package edu.miu.cs545.group01.online.market.domain;

import edu.miu.cs545.group01.online.market.domain.enums.BillingInfoStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BankAccount")
public class BillingInfoBankAccount extends BillingInfo {

    private String accountName;

    private String accountNumber;

    private String routingNumber;

    public BillingInfoBankAccount() {
    }

    public BillingInfoBankAccount(Buyer buyer, String accountName, String accountNumber, String routingNumber, Address billingAddress, BillingInfoStatus status) {
        super(buyer, billingAddress, status);
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.routingNumber = routingNumber;
    }


    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }


    @Override
    public String toString() {
        return "Bank account: " + accountName +
                        ", " + accountNumber +
                        ", " + routingNumber;
    }
}
