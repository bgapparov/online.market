package edu.miu.cs545.group01.online.market.repository;

import edu.miu.cs545.group01.online.market.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
