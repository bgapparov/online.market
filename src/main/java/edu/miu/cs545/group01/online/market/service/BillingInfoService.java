package edu.miu.cs545.group01.online.market.service;

import edu.miu.cs545.group01.online.market.domain.BillingInfo;

import java.util.List;

public interface BillingInfoService {
    BillingInfo createBilling(BillingInfo billingInfo);
    List<BillingInfo> getBills();
    BillingInfo getBilling(Long id);
    void deleteBilling(long id) throws Exception;
}
