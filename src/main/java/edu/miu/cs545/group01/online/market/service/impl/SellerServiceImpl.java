package edu.miu.cs545.group01.online.market.service.impl;

import edu.miu.cs545.group01.online.market.domain.Seller;
import edu.miu.cs545.group01.online.market.repository.SellerRepository;
import edu.miu.cs545.group01.online.market.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerRepository sellerRepository;
    @Override
    public List<Seller> getPendingSellers() {

        return sellerRepository.findAllByStatusPending();
    }
}
