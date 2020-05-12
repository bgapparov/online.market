package edu.miu.cs545.group01.online.market.repository;

import edu.miu.cs545.group01.online.market.domain.Address;
import edu.miu.cs545.group01.online.market.domain.BillingInfo;
import edu.miu.cs545.group01.online.market.domain.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByBuyer(Buyer buyer);
}
