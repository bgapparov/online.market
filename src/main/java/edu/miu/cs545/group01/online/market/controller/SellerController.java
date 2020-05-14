package edu.miu.cs545.group01.online.market.controller;

import edu.miu.cs545.group01.online.market.domain.Product;
import edu.miu.cs545.group01.online.market.domain.ProductImageModel;
import edu.miu.cs545.group01.online.market.domain.Seller;
import edu.miu.cs545.group01.online.market.domain.enums.OrderStatus;
import edu.miu.cs545.group01.online.market.domain.enums.ProductStatus;
import edu.miu.cs545.group01.online.market.exception.OrderStatusException;
import edu.miu.cs545.group01.online.market.exception.RemoveException;
import edu.miu.cs545.group01.online.market.exception.UploadImageException;
import edu.miu.cs545.group01.online.market.helper.Helper;
import edu.miu.cs545.group01.online.market.service.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/seller")
public class SellerController extends BaseController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OrderService orderService;
    @Autowired
    ServletContext servletContext;
    @Autowired
    SellerService sellerService;
    @Autowired
    FollowService followService;

    @GetMapping("/my-products")
    public String myProducts(Model model){
        Seller curSeller = getCurrentSeller();
        model.addAttribute("myProducts", productService.findAllProductsBySeller(curSeller));
        return "seller/my-products";
    }
    @GetMapping("/product/add")
    public String addProduct(@ModelAttribute("product") Product product, Model model){
        model.addAttribute("categories", categoryService.allCategories());
        return "seller/add-product";
    }
    @PostMapping("/product/add")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) throws UploadImageException, IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.allCategories());
            return "seller/add-product";
        }
        MultipartFile productImage = product.getImage();
        if (productImage != null && !productImage.isEmpty()) {
            String extension = Helper.getExtension(productImage.getOriginalFilename());
            if(!Helper.isItImage(extension) ){
                throw new UploadImageException("File must be image");
            }

            String imgFileName = java.util.UUID.randomUUID() + extension;
            String dir = Helper.getImagesFolder(servletContext);
            productImage.transferTo(new File(dir, imgFileName));
            product.setImgName(imgFileName);
        }
        product.setSeller(getCurrentSeller());
        product.setStatus(ProductStatus.ACTIVE);
        productService.createProduct(product);

        return "redirect:/seller/my-products";
    }
    @GetMapping("/product/edit/{productId}")
    public String editProduct(@PathVariable("productId") long productId, Model model) throws NotFoundException {
        model.addAttribute("categories", categoryService.allCategories());
        Product product = productService.getProduct(productId);
        model.addAttribute("product", product);
        return "seller/edit-product";
    }
    @PostMapping("/product/edit/{productId}")
    public String updateProduct(@PathVariable("productId") long productId, @Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) throws NotFoundException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.allCategories());
            return "seller/edit-product";
        }
        productService.updateProduct(productId, product);

        return "redirect:/seller/my-products";
    }
    @GetMapping("/product/edit/image/{productId}")
    public String editProductImage(@PathVariable("productId") long productId, Model model) throws NotFoundException {
        Product product = productService.getProduct(productId);

        if(product.getSeller().getId() != getCurrentSeller().getId()){
            throw new NotFoundException("Product is not found");
        }
        ProductImageModel productImageModel = new ProductImageModel(product.getId(), product.getImgName());

        model.addAttribute("productImageModel", productImageModel);
        return "seller/edit-product-image";
    }
    @PostMapping("/product/edit/image/{productId}")
    public String updateProductImage(@PathVariable("productId") long productId, @Valid @ModelAttribute("productImageModel") ProductImageModel productImageModel, BindingResult bindingResult, Model model) throws NotFoundException, IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("productImageModel", productImageModel);
            return "seller/edit-product-image";
        }
        MultipartFile image = productImageModel.getImage();
        String imgFileName = "";
        if (image != null && !image.isEmpty()) {
            String extension = Helper.getExtension(image.getOriginalFilename());
            if(!Helper.isItImage(extension) ){
                throw new UploadImageException("File must be image");
            }

            imgFileName = java.util.UUID.randomUUID() + extension;
            String dir = Helper.getImagesFolder(servletContext);
            image.transferTo(new File(dir, imgFileName));
        }
        productService.updatedProductImage(productId, imgFileName);
        return "redirect:/seller/my-products";

    }
    @DeleteMapping("/product/remove/{productId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("productId") long productId) throws NotFoundException, RemoveException {
        productService.deleteProduct(productId);
    }

    @GetMapping("/order/list")
    public String orderList(Model model){
        model.addAttribute("orders", orderService.getMyOrders(getCurrentSeller()));
        return "seller/order/list";
    }

    @DeleteMapping("/order/cancel/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelOrder(@PathVariable("orderId") long orderId) throws NotFoundException, OrderStatusException {
        orderService.setStatus(getCurrentSeller(), orderId, OrderStatus.CANCELED);
    }
    @PutMapping("/order/shipped/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void shippedOrder(@PathVariable("orderId") long orderId) throws NotFoundException, OrderStatusException {
        orderService.setStatus(getCurrentSeller(), orderId, OrderStatus.SHIPPED);
    }
    @PutMapping("/order/delivered/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deliveredOrder(@PathVariable("orderId") long orderId) throws NotFoundException, OrderStatusException {
        orderService.setStatus(getCurrentSeller(), orderId, OrderStatus.DELIVERED);
    }
    @GetMapping("/get/{sellerId}")
    public String getSeller(@PathVariable("sellerId") long sellerId, Model model) throws NotFoundException{
        Seller seller = sellerService.getSellerById(sellerId);
        model.addAttribute("seller", seller);
        model.addAttribute("isFollow", followService.isFollow(seller, getCurrentBuyer()));
        model.addAttribute("categories", categoryService.allCategories());
        return "seller/seller-page";
    }
}
