package edu.miu.cs545.group01.online.market.service;

import edu.miu.cs545.group01.online.market.domain.Address;
import edu.miu.cs545.group01.online.market.domain.BillingInfo;
import javassist.NotFoundException;

import java.util.List;

public interface BillingInfoService {
    BillingInfo createBilling(BillingInfo billingInfo);
    List<BillingInfo> getBills();
    BillingInfo getBilling(Long id);
    BillingInfo updateAddress(long id, BillingInfo address) throws NotFoundException;
    void deleteBilling(long id) throws NotFoundException;
}
