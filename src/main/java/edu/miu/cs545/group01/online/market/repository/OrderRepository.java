package edu.miu.cs545.group01.online.market.repository;

import edu.miu.cs545.group01.online.market.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
