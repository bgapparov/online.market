package edu.miu.cs545.group01.online.market.controller;

import edu.miu.cs545.group01.online.market.domain.Category;
import edu.miu.cs545.group01.online.market.domain.Product;
import edu.miu.cs545.group01.online.market.domain.User;
import edu.miu.cs545.group01.online.market.domain.enums.ProductStatus;
import edu.miu.cs545.group01.online.market.domain.enums.Role;
import edu.miu.cs545.group01.online.market.repository.SellerRepository;
import edu.miu.cs545.group01.online.market.service.CategoryService;
import edu.miu.cs545.group01.online.market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController extends BaseController {
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @GetMapping
    public String mainPage(@RequestParam(value = "category", required = false, defaultValue = "-1") Integer category, Model model){
//        Product prod = new Product("iPhone", new Category("phone"), 1000, ProductStatus.ACTIVE, ".jpg",
//                "Fully unlocked and compatible with any carrier of choice (e.g. AT&T, T-Mobile, Sprint, Verizon, US-Cellular, Cricket, Metro, etc.).\n",
//                sellerRepository.findFirstByOrderByNameAsc()
//        );
        List<Product> products = productService.getTop9Products(category);

        model.addAttribute("products", products);
        model.addAttribute("categories", categoryService.allCategories());

//        model.addAttribute("product", prod);
        return "index";
    }
    @GetMapping("/homepage")
    public String homepage(HttpServletRequest request){
        User curUser = getCurrentUser();
        if(curUser == null){
            return "redirect:/";
        }
        return "homepage";
//        if(request.isUserInRole(Role.))
    }
}
