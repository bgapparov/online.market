package edu.miu.cs545.group01.online.market.service;

import edu.miu.cs545.group01.online.market.domain.Product;
import edu.miu.cs545.group01.online.market.domain.Seller;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getTop9Products(int categoryId);
    List<Product> findAllProductsBySeller(Seller seller);
    Product  getProduct(Long id);
    Product updateProduct(Long id, Product product) throws Exception;
    Product deleteProduct(Long id) throws Exception;
}
