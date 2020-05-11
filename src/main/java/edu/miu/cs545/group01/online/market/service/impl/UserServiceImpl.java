package edu.miu.cs545.group01.online.market.service.impl;

import edu.miu.cs545.group01.online.market.domain.*;
import edu.miu.cs545.group01.online.market.domain.enums.UserStatus;
import edu.miu.cs545.group01.online.market.repository.BuyerRepository;
import edu.miu.cs545.group01.online.market.repository.SellerRepository;
import edu.miu.cs545.group01.online.market.repository.UserRepository;
import edu.miu.cs545.group01.online.market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BuyerRepository buyerRepository;
    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Admin createAdmin(Admin admin) {
        return null;
    }

    @Override
    public List<Admin> getAdmins() {
        return null;
    }

    @Override
    public Admin getAdmin(Long id) {
        return null;
    }

    @Override
    public Admin updateAdmin(Long id, Admin admin) throws Exception {
        return null;
    }

    @Override
    public Admin deleteAdmin(Long id) throws Exception {
        return null;
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

    @Override
    public Seller createNewSeller(UserRegistrationModel user) {
        Seller seller = new Seller(user.getUserName(), user.getEmail(), passwordEncoder.encode(user.getPassword()), UserStatus.PENDING);
        return sellerRepository.save(seller);
    }

    @Override
    public List<Seller> getSellers() {
        return null;
    }

    @Override
    public Seller getSeller(Long id) {
        return null;
    }

    @Override
    public Seller updateSeller(Long id, Seller seller) throws Exception {
        return null;
    }

    @Override
    public Seller deleteSeller(Long id) throws Exception {
        return null;
    }
}
