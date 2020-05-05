package edu.miu.cs545.group01.online.market.repository;

import edu.miu.cs545.group01.online.market.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
