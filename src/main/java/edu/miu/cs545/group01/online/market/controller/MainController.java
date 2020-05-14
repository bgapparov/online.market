package edu.miu.cs545.group01.online.market.controller;

import edu.miu.cs545.group01.online.market.domain.Product;
import edu.miu.cs545.group01.online.market.service.CategoryService;
import edu.miu.cs545.group01.online.market.service.GainPointService;
import edu.miu.cs545.group01.online.market.service.ProductService;
import edu.miu.cs545.group01.online.market.service.ShoppingCartService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class MainController extends BaseController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    GainPointService gainPointService;

    @GetMapping
    public String mainPage(@RequestParam(value = "category", required = false, defaultValue = "-1") Integer category, Model model){
        List<Product> products = productService.getTop9Products(category);
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryService.allCategories());
        return "index";
    }

    @GetMapping("/product/{productId}")
    public String productPage(@PathVariable("productId") long productId, Model model) throws NotFoundException {
        model.addAttribute("product", productService.getProduct(productId));
        model.addAttribute("categories", categoryService.allCategories());
        model.addAttribute("isproductincart", shoppingCartService.checkProductInCart(getCurrentBuyer(), productId));
        model.addAttribute("seller", productService.getSellerByProductId(getCurrentBuyer(), productId));
        return "product";
    }

    @GetMapping("/homepage")
    public String homepage(Model model){
        resetAuthentication();
        model.addAttribute("myPoints", gainPointService.getFreePoints(getCurrentBuyer()));
        return "homepage";
    }
}
