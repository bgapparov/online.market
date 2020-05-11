package edu.miu.cs545.group01.online.market.repository;

import edu.miu.cs545.group01.online.market.domain.BillingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillingInfoRepository extends JpaRepository<BillingInfo, Long> {
    @Query(value = "from BillingInfo where status = 'ACTIVE'")
    List<BillingInfo> findAllByStatusActive();
}
