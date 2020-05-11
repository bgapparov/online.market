package edu.miu.cs545.group01.online.market.service;

import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.UserRegistrationModel;

import java.util.List;

public interface BuyerService {

    Buyer getBuyerByEmail(String email);

    Buyer createNewBuyer(UserRegistrationModel user);
    List<Buyer> getBuyers();
    Buyer  getBuyer(Long id);
    Buyer updateBuyer(Long id, Buyer buyer) throws Exception;
    Buyer deleteBuyer(Long id) throws Exception;

}
