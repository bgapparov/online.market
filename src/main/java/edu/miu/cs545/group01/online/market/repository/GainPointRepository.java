package edu.miu.cs545.group01.online.market.repository;

import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.GainPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GainPointRepository extends JpaRepository<GainPoint, Long> {
    List<GainPoint> findAllByBuyer(Buyer buyer);
}
