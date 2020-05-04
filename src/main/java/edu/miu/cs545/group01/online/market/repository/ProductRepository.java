package edu.miu.cs545.group01.online.market.repository;

import edu.miu.cs545.group01.online.market.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
