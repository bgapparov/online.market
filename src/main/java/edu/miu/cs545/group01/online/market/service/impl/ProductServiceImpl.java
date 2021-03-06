package edu.miu.cs545.group01.online.market.service.impl;

import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.Category;
import edu.miu.cs545.group01.online.market.domain.Product;
import edu.miu.cs545.group01.online.market.domain.Seller;
import edu.miu.cs545.group01.online.market.domain.enums.ProductStatus;
import edu.miu.cs545.group01.online.market.exception.RemoveException;
import edu.miu.cs545.group01.online.market.repository.CategoryRepository;
import edu.miu.cs545.group01.online.market.repository.ProductRepository;
import edu.miu.cs545.group01.online.market.service.ProductService;
import javassist.NotFoundException;
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
            result = productRepository.findTop9ByStatusOrderByIdDesc(ProductStatus.ACTIVE);

        }else {
            result = productRepository.findTop9ByCategoryAndStatusOrderByIdDesc(categoryRepository.getOne(categoryId), ProductStatus.ACTIVE);
        }
        return result;
    }

    @Override
    public List<Product> findAllProductsBySeller(Seller seller) {
        return productRepository.findAllBySellerAndStatus(seller, ProductStatus.ACTIVE);
    }

    @Override
    public Product getProduct(Long id) throws NotFoundException {
        return productRepository.findById(id).orElseThrow(()->new NotFoundException("Product is not found. ProductId = "+id));
    }

    @Override
    public Seller getSellerByProductId(Buyer buyer, Long id) throws NotFoundException {
        return productRepository.findById(id).orElseThrow(()->new NotFoundException("Product is not found. ProductId = "+id)).getSeller();
    }

    @Override
    public Product updateProduct(Long id, Product product) throws NotFoundException {
        Product prod = productRepository.findById(id).orElseThrow(()->new NotFoundException("Product is not found. ProductId = "+id));
        prod.setCategory(product.getCategory());
        prod.setTitle(product.getTitle());
        prod.setPrice(product.getPrice());
        prod.setDescription(product.getDescription());
        return productRepository.save(prod);
    }

    @Override
    public Product deleteProduct(Long id) throws NotFoundException, RemoveException {
        Product product = productRepository.findById(id).orElseThrow(()->new NotFoundException("Product is not found. ProductId="+id));
        if(product.getOrderProducts().size()>0){
            throw new RemoveException("Product cannot be removed! Product already purchased.");
        }
        product.setStatus(ProductStatus.REMOVED);
        return productRepository.save(product);
    }

    @Override
    public void updatedProductImage(long productId, String imgFileName) throws NotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(()->new NotFoundException("Product is not found"));
        product.setImgName(imgFileName);
        productRepository.save(product);
    }
}
