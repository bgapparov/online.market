package edu.miu.cs545.group01.online.market.repository;

import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.Category;
import edu.miu.cs545.group01.online.market.domain.Product;
import edu.miu.cs545.group01.online.market.domain.Seller;
import edu.miu.cs545.group01.online.market.domain.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findTop9ByStatusOrderByIdDesc(ProductStatus status);
//    @Query("from Product where status = ACTIVE and category = :category order by id desc")
    List<Product> findTop9ByCategoryAndStatusOrderByIdDesc(Category category, ProductStatus status);

//    @Query("from Product where status <> 'REMOVED' and seller = :seller order by id desc")
    List<Product> findAllBySellerAndStatus(Seller seller, ProductStatus status);

}
