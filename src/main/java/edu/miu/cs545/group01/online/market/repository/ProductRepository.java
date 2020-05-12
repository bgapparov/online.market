package edu.miu.cs545.group01.online.market.repository;

import edu.miu.cs545.group01.online.market.domain.Category;
import edu.miu.cs545.group01.online.market.domain.Product;
import edu.miu.cs545.group01.online.market.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findTop9ByOrderByIdDesc();
    List<Product> findTop9ByCategoryOrderByIdDesc(Category category);

    @Query("from Product where status <> 'REMOVED' and seller = :seller")
    List<Product> findAllNotRemovedProductsBySeller(Seller seller);

}
