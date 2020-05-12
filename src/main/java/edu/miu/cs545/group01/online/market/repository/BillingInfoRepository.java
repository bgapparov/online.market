package edu.miu.cs545.group01.online.market.repository;

import edu.miu.cs545.group01.online.market.domain.BillingInfo;
import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.enums.BillingInfoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillingInfoRepository extends JpaRepository<BillingInfo, Long> {
    List<BillingInfo> findAllByStatusAndBuyer(BillingInfoStatus status, Buyer buyer);
}
