package edu.miu.cs545.group01.online.market.service;

import edu.miu.cs545.group01.online.market.domain.Admin;
import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.Seller;

import java.util.List;

public interface UserService {
    Admin createAdmin(Admin admin);
    List<Admin> getAdmins();
    Admin  getAdmin(Long id);
    Admin updateAdmin(Long id, Admin admin) throws Exception;
    Admin deleteAdmin(Long id) throws Exception;

    Buyer createBuyer(Buyer buyer);
    List<Buyer> getBuyers();
    Buyer  getBuyer(Long id);
    Buyer updateBuyer(Long id, Buyer buyer) throws Exception;
    Buyer deleteBuyer(Long id) throws Exception;

    Seller createSeller(Seller seller);
    List<Seller> getSellers();
    Seller  getSeller(Long id);
    Seller updateSeller(Long id, Seller seller) throws Exception;
    Seller deleteSeller(Long id) throws Exception;
}
