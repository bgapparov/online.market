package edu.miu.cs545.group01.online.market.service;

import edu.miu.cs545.group01.online.market.domain.Admin;
import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.Seller;

import java.util.List;

public interface UserService {
    public Admin createAdmin(Admin admin);
    public List<Admin> getAdmins();
    public Admin  getAdmin(Long id);
    public Admin updateAdmin(Long id, Admin admin) throws Exception;
    public Admin deleteAdmin(Long id) throws Exception;

    public Buyer createBuyer(Buyer buyer);
    public List<Buyer> getBuyers();
    public Buyer  getBuyer(Long id);
    public Buyer updateBuyer(Long id, Buyer buyer) throws Exception;
    public Buyer deleteBuyer(Long id) throws Exception;

    public Seller createSeller(Seller seller);
    public List<Seller> getSellers();
    public Seller  getSeller(Long id);
    public Seller updateSeller(Long id, Seller seller) throws Exception;
    public Seller deleteSeller(Long id) throws Exception;
}
