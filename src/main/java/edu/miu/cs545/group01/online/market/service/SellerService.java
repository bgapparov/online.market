package edu.miu.cs545.group01.online.market.service;

import edu.miu.cs545.group01.online.market.domain.Seller;

import java.util.List;

public interface SellerService {
    List<Seller> getPendingSellers();
}
