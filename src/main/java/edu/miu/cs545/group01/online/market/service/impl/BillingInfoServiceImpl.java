package edu.miu.cs545.group01.online.market.service.impl;

import edu.miu.cs545.group01.online.market.domain.*;
import edu.miu.cs545.group01.online.market.domain.enums.BillingInfoStatus;
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
    public List<BillingInfo> getBillsByBuyer(Buyer buyer) {
        return billingInfoRepository.findAllByStatusAndBuyer(BillingInfoStatus.ACTIVE, buyer);
    }

    @Override
    public BillingInfo getBilling(Long id){
        return billingInfoRepository.findById(id).orElse(null);
    }

    @Override
    public BillingInfo updateCreditCard(long id, BillingInfoCreditCard card) throws NotFoundException {
        BillingInfoCreditCard bill = (BillingInfoCreditCard)billingInfoRepository.findById(id).orElseThrow(()->new NotFoundException("BillingInfoCreditCard is not found"));
        bill.setCardNo(card.getCardNo());
        bill.setCardName(card.getCardName());
        bill.setExpirationDate(card.getExpirationDate());
        bill.setSecurityNumber(card.getSecurityNumber());
        return billingInfoRepository.save(bill);
    }

    @Override
    public BillingInfo updateBankAccount(long id, BillingInfoBankAccount bank) throws NotFoundException {
        BillingInfoBankAccount bill = (BillingInfoBankAccount)billingInfoRepository.findById(id).orElseThrow(()->new NotFoundException("BillingInfoBankAccount is not found"));
        bill.setAccountName(bank.getAccountName());
        bill.setAccountNumber(bank.getAccountNumber());
        bill.setRoutingNumber(bank.getRoutingNumber());
        return billingInfoRepository.save(bill);
    }

    @Override
    public void deleteBilling(long id) throws NotFoundException {
        billingInfoRepository.deleteById(id);
    }
}
