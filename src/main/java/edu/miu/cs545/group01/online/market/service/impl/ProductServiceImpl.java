package edu.miu.cs545.group01.online.market.service.impl;

import edu.miu.cs545.group01.online.market.domain.Category;
import edu.miu.cs545.group01.online.market.domain.Product;
import edu.miu.cs545.group01.online.market.domain.Seller;
import edu.miu.cs545.group01.online.market.repository.CategoryRepository;
import edu.miu.cs545.group01.online.market.repository.ProductRepository;
import edu.miu.cs545.group01.online.market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getTop9Products(int categoryId) {
        List<Product> result;
        if(categoryId<0){
            result = productRepository.findTop9ByOrderByIdDesc();

        }else {
            result = productRepository.findTop9ByCategoryOrderByIdDesc(categoryRepository.getOne(categoryId));
        }
        return result;
    }

    @Override
    public List<Product> findAllProductsBySeller(Seller seller) {
        return productRepository.findAllNotRemovedProductsBySeller(seller);
    }

    @Override
    public Product getProduct(Long id) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws Exception {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) throws Exception {
        return null;
    }
}
