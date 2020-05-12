package edu.miu.cs545.group01.online.market.repository;

import edu.miu.cs545.group01.online.market.domain.BillingInfo;
import edu.miu.cs545.group01.online.market.domain.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillingInfoRepository extends JpaRepository<BillingInfo, Long> {
    List<BillingInfo> findAllByBuyer(Buyer buyer);
}
