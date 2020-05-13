package edu.miu.cs545.group01.online.market.service;

import edu.miu.cs545.group01.online.market.domain.Seller;
import javassist.NotFoundException;

import java.util.List;

public interface SellerService {
    List<Seller> getPendingSellers();
    void approveSeller(long sellerId) throws NotFoundException;
    void rejectSeller(long sellerId) throws NotFoundException;

    Seller getSellerById(long sellerId) throws NotFoundException;
}
