package edu.miu.cs545.group01.online.market.service.impl;

import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.UserRegistrationModel;
import edu.miu.cs545.group01.online.market.domain.enums.UserStatus;
import edu.miu.cs545.group01.online.market.repository.BuyerRepository;
import edu.miu.cs545.group01.online.market.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    BuyerRepository buyerRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Buyer getBuyerByEmail(String email) {
        return buyerRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Buyer createNewBuyer(UserRegistrationModel user) {
        Buyer buyer = new Buyer(user.getUserName(), user.getEmail(), passwordEncoder.encode(user.getPassword()), UserStatus.ACTIVE);
        return buyerRepository.save(buyer);
    }

    @Override
    public List<Buyer> getBuyers() {
        return null;
    }

    @Override
    public Buyer getBuyer(Long id) {
        return null;
    }

    @Override
    public Buyer updateBuyer(Long id, Buyer buyer) throws Exception {
        return null;
    }

    @Override
    public Buyer deleteBuyer(Long id) throws Exception {
        return null;
    }

}
