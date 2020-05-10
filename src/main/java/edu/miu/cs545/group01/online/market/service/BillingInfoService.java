package edu.miu.cs545.group01.online.market.service;

import edu.miu.cs545.group01.online.market.domain.BillingInfo;

import java.util.List;

public interface BillingInfoService {
    BillingInfo createBilling(BillingInfo billingInfo);
    List<BillingInfo> getBillingInfos();
    BillingInfo findByBillingInfoById(Long id);
}
