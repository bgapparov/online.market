package edu.miu.cs545.group01.online.market.repository;

import edu.miu.cs545.group01.online.market.domain.BillingInfo;
import edu.miu.cs545.group01.online.market.domain.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
