package edu.miu.cs545.group01.online.market.repository;

import edu.miu.cs545.group01.online.market.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
