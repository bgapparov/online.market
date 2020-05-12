package edu.miu.cs545.group01.online.market.controller;

import edu.miu.cs545.group01.online.market.domain.Product;
import edu.miu.cs545.group01.online.market.domain.Seller;
import edu.miu.cs545.group01.online.market.domain.enums.ProductStatus;
import edu.miu.cs545.group01.online.market.exception.UploadImageException;
import edu.miu.cs545.group01.online.market.helper.Helper;
import edu.miu.cs545.group01.online.market.service.CategoryService;
import edu.miu.cs545.group01.online.market.service.ProductService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/seller")
public class SellerController extends BaseController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    ServletContext servletContext;

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
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult) throws UploadImageException, IOException {
        if (bindingResult.hasErrors()) {
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
    public String updateProduct(@PathVariable("productId") long productId, @Valid @ModelAttribute("product") Product product, BindingResult bindingResult) throws NotFoundException {
        if (bindingResult.hasErrors()) {
            return "seller/edit-product";
        }
        productService.updateProduct(productId, product);

        return "redirect:/seller/my-products";
    }
}
