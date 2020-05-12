package edu.miu.cs545.group01.online.market.service.impl;

import edu.miu.cs545.group01.online.market.domain.*;
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
    public BillingInfo createCreditCard(BillingInfoCreditCard card, Buyer buyer){
        card.setBuyer(buyer);
        return billingInfoRepository.save(card);
    }

    @Override
    public BillingInfo createBankAccount(BillingInfoBankAccount bank, Buyer buyer){
        bank.setBuyer(buyer);
        return billingInfoRepository.save(bank);
    }

    @Override
    public List<BillingInfo> getBillsByBuyer(Buyer buyer) {
        return billingInfoRepository.findAllByBuyer(buyer);
    }

    @Override
    public BillingInfo getBilling(Buyer buyer, Long id){
        BillingInfo result = billingInfoRepository.findById(id).orElse(null);
        if(result != null && result.getBuyer().getId() != buyer.getId()){
            return null;
        }
        return result;
    }

    @Override
    public BillingInfo updateCreditCard(Buyer buyer, long id, BillingInfoCreditCard card) throws NotFoundException {
        BillingInfoCreditCard result = (BillingInfoCreditCard)billingInfoRepository.findById(id).orElseThrow(()->new NotFoundException("BillingInfoCreditCard is not found"));
        if(result != null && result.getBuyer().getId() != buyer.getId()){
            return null;
        }
        result.setCardNo(card.getCardNo());
        result.setCardName(card.getCardName());
        result.setExpirationDate(card.getExpirationDate());
        result.setSecurityNumber(card.getSecurityNumber());
        return billingInfoRepository.save(result);
    }

    @Override
    public BillingInfo updateBankAccount(Buyer buyer, long id, BillingInfoBankAccount bank) throws NotFoundException {
        BillingInfoBankAccount result = (BillingInfoBankAccount)billingInfoRepository.findById(id).orElseThrow(()->new NotFoundException("BillingInfoBankAccount is not found"));
        if(result != null && result.getBuyer().getId() != buyer.getId()){
            return null;
        }
        result.setAccountName(bank.getAccountName());
        result.setAccountNumber(bank.getAccountNumber());
        result.setRoutingNumber(bank.getRoutingNumber());
        return billingInfoRepository.save(result);
    }

    @Override
    public void deleteBilling(Buyer buyer, long id) throws NotFoundException {
        BillingInfo result = billingInfoRepository.findById(id).orElseThrow(()->new NotFoundException("Address is not found"));
        if(result != null && result.getBuyer().getId() != buyer.getId()){
            return;
        }
        billingInfoRepository.deleteById(result.getId());
    }
}
