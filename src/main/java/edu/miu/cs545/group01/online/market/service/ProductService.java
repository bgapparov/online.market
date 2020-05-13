package edu.miu.cs545.group01.online.market.service;

import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.Product;
import edu.miu.cs545.group01.online.market.domain.Seller;
import edu.miu.cs545.group01.online.market.exception.RemoveException;
import javassist.NotFoundException;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getTop9Products(int categoryId);
    List<Product> findAllProductsBySeller(Seller seller);
    Product  getProduct(Long id) throws NotFoundException;
    Product updateProduct(Long id, Product product) throws NotFoundException;
    Product deleteProduct(Long id) throws NotFoundException, RemoveException;

    void updatedProductImage(long productId, String imgFileName) throws NotFoundException;
}
