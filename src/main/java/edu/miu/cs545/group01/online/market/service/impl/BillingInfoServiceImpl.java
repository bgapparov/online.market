package edu.miu.cs545.group01.online.market.service.impl;

import edu.miu.cs545.group01.online.market.domain.BillingInfo;
import edu.miu.cs545.group01.online.market.repository.BillingInfoRepository;
import edu.miu.cs545.group01.online.market.service.BillingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingInfoServiceImpl implements BillingInfoService {

    @Autowired
    BillingInfoRepository billingInfoRepository;

    @Override
    public BillingInfo createBilling(BillingInfo billingInfo){
        return billingInfoRepository.save(billingInfo);
    }

    @Override
    public List<BillingInfo> getBills(){
        return billingInfoRepository.findAll();
    }

    @Override
    public BillingInfo getBilling(Long id){
        return billingInfoRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteBilling(long id){
        billingInfoRepository.deleteById(id);
    }
}
