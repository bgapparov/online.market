package edu.miu.cs545.group01.online.market.repository;

import edu.miu.cs545.group01.online.market.domain.BillingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingInfoRepository extends JpaRepository<BillingInfo, Long> {
}
