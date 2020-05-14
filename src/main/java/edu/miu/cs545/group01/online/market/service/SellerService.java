package edu.miu.cs545.group01.online.market.service;

import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.Seller;
import edu.miu.cs545.group01.online.market.domain.ShoppingCart;
import javassist.NotFoundException;

import java.util.List;

public interface SellerService {
    List<Seller> getPendingSellers();
    void approveSeller(long sellerId) throws NotFoundException;
    void rejectSeller(long sellerId) throws NotFoundException;
    // ShoppingCart getSellerByProductId(Buyer buyer, long sellerId) throws NotFoundException;
    Seller getSellerById(long sellerId) throws NotFoundException;
}
