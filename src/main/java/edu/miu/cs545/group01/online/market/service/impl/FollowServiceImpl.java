package edu.miu.cs545.group01.online.market.service.impl;

import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.Follows;
import edu.miu.cs545.group01.online.market.domain.Seller;
import edu.miu.cs545.group01.online.market.domain.enums.UserStatus;
import edu.miu.cs545.group01.online.market.repository.FollowsRepository;
import edu.miu.cs545.group01.online.market.repository.SellerRepository;
import edu.miu.cs545.group01.online.market.service.FollowService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private FollowsRepository followsRepository;

    @Override
    public Follows followSeller(Buyer buyer, long sellerId) throws NotFoundException {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(()->new NotFoundException("Seller does not exist"));
        return followsRepository.save(new Follows(seller, buyer));
    }

    @Override
    public Follows unfollowSeller(Buyer buyer, long sellerId) throws NotFoundException {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(()->new NotFoundException("Seller does not exist"));
        Follows follows = followsRepository.findBySellerAndBuyer(seller, buyer);
        if(follows == null){
            return  null;
        }
        followsRepository.delete(follows);
        return follows;
    }


    @Override
    public boolean isFollow(Seller seller, Buyer buyer) {
        if(seller == null || buyer == null) {
            return false;
        }
        return followsRepository.findBySellerAndBuyer(seller, buyer) != null;
    }
}
