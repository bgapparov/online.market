package edu.miu.cs545.group01.online.market.service;

import edu.miu.cs545.group01.online.market.domain.*;
import javassist.NotFoundException;

import java.util.List;

public interface BillingInfoService {
    BillingInfo createCreditCard(BillingInfoCreditCard card, Buyer buyer);
    BillingInfo createBankAccount(BillingInfoBankAccount bank, Buyer buyer);
    List<BillingInfo> getBillsByBuyer(Buyer buyer);
    BillingInfo getBilling(Buyer buyer, Long id);
    BillingInfo updateCreditCard(Buyer buyer, long id, BillingInfoCreditCard card) throws NotFoundException;
    BillingInfo updateBankAccount(Buyer buyer, long id, BillingInfoBankAccount bank) throws NotFoundException;
    void deleteBilling(Buyer buyer, long id) throws NotFoundException;
}
