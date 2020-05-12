package edu.miu.cs545.group01.online.market.service;

import edu.miu.cs545.group01.online.market.domain.*;
import javassist.NotFoundException;

import java.util.List;

public interface BillingInfoService {
    BillingInfo createBilling(BillingInfo billingInfo);
    List<BillingInfo> getBillsByBuyer(Buyer buyer);
    BillingInfo getBilling(Long id);
    BillingInfo updateCreditCard(long id, BillingInfoCreditCard card) throws NotFoundException;
    BillingInfo updateBankAccount(long id, BillingInfoBankAccount bank) throws NotFoundException;
    void deleteBilling(long id) throws NotFoundException;
}
