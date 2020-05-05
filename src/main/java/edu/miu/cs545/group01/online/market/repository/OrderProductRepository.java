package edu.miu.cs545.group01.online.market.repository;

import edu.miu.cs545.group01.online.market.domain.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
