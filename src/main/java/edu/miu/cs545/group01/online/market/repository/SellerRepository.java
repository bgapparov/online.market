package edu.miu.cs545.group01.online.market.repository;

import edu.miu.cs545.group01.online.market.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findFirstByOrderByNameAsc();
    @Query("from Seller where status = 'PENDING'")
    List<Seller> findAllByStatusPending();
}
