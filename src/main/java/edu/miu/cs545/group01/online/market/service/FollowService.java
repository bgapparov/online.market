package edu.miu.cs545.group01.online.market.service;

import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.Follows;
import edu.miu.cs545.group01.online.market.domain.Seller;
import javassist.NotFoundException;

import java.util.List;

public interface FollowService {

    List<Seller> getPendingSellers();
    Follows followSeller(Buyer buyer, long sellerId) throws NotFoundException;
    Follows unfollowSeller(Buyer buyer, long sellerId) throws NotFoundException;
}
