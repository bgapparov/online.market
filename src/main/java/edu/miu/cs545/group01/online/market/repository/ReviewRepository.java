package edu.miu.cs545.group01.online.market.repository;

import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.OrderProduct;
import edu.miu.cs545.group01.online.market.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("from Review where status = 'CREATED'")
    List<Review> findAllByStatusCreated();

    Optional<Review> findByOrderProductAndBuyer(OrderProduct orderProduct, Buyer buyer);
}
