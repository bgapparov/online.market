package edu.miu.cs545.group01.online.market.service.impl;

import edu.miu.cs545.group01.online.market.domain.Address;
import edu.miu.cs545.group01.online.market.domain.BillingInfo;
import edu.miu.cs545.group01.online.market.repository.BillingInfoRepository;
import edu.miu.cs545.group01.online.market.service.BillingInfoService;
import javassist.NotFoundException;
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

    public BillingInfo updateAddress(long id, BillingInfo billing) throws NotFoundException{
        BillingInfo bill = billingInfoRepository.findById(id).orElseThrow(()->new NotFoundException("Address is not found"));
        bill.setCardNo(billing.getCardNo());
        bill.setCardName(billing.getCardName());
        bill.setExpirationDate(billing.getExpirationDate());
        bill.setSecurityNumber(billing.getSecurityNumber());
        bill.setAccountName(billing.getAccountName());
        bill.setAccountNumber(billing.getAccountNumber());
        bill.setRoutingNumber(billing.getRoutingNumber());
        return billingInfoRepository.save(bill);
    }

    @Override
    public void deleteBilling(long id) throws NotFoundException {
        billingInfoRepository.deleteById(id);
    }
}
