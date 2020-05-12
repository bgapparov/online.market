package edu.miu.cs545.group01.online.market.repository;

import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.Follows;
import edu.miu.cs545.group01.online.market.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowsRepository extends JpaRepository<Follows, Long> {
    Follows findBySellerAndBuyer(Seller seller, Buyer buyer);
}
