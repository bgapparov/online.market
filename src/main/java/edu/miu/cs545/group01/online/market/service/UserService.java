package edu.miu.cs545.group01.online.market.service;

import edu.miu.cs545.group01.online.market.domain.*;
import edu.miu.cs545.group01.online.market.domain.UserRegistrationModel;

import java.util.List;

public interface UserService {
    User getUserByEmail(String email);

    Admin createAdmin(Admin admin);
    List<Admin> getAdmins();
    Admin  getAdmin(Long id);
    Admin updateAdmin(Long id, Admin admin) throws Exception;
    Admin deleteAdmin(Long id) throws Exception;

    Buyer createNewBuyer(UserRegistrationModel user);
    List<Buyer> getBuyers();
    Buyer  getBuyer(Long id);
    Buyer updateBuyer(Long id, Buyer buyer) throws Exception;
    Buyer deleteBuyer(Long id) throws Exception;

    Seller createNewSeller(UserRegistrationModel user);
    List<Seller> getSellers();
    Seller  getSeller(Long id);
    Seller updateSeller(Long id, Seller seller) throws Exception;
    Seller deleteSeller(Long id) throws Exception;
}
