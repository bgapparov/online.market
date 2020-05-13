package edu.miu.cs545.group01.online.market.repository;

import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.Product;
import edu.miu.cs545.group01.online.market.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findAllByBuyer(Buyer buyer);

    Optional<ShoppingCart> findByBuyerAndProduct(Buyer buyer, Product product);

    void deleteAllByBuyer(Buyer buyer);
}
