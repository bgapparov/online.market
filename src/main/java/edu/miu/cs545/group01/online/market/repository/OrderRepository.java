package edu.miu.cs545.group01.online.market.repository;

import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.Order;
import edu.miu.cs545.group01.online.market.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByBuyer(Buyer buyer);
    List<Order> findAllBySeller(Seller seller);
    Optional<Order> findByBuyerAndId(Buyer buyer, long id);
    Optional<Order> findBySellerAndId(Seller seller, long id);
}
