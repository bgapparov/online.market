package edu.miu.cs545.group01.online.market.controller;

import edu.miu.cs545.group01.online.market.domain.Category;
import edu.miu.cs545.group01.online.market.domain.Product;
import edu.miu.cs545.group01.online.market.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class MainController extends BaseController {

    @Autowired
    SellerRepository sellerRepository;
    @GetMapping
    public String mainPage(Model model){
        Product prod = new Product("iPhone", new Category("phone"), 1000, "Active", ".jpg",
                "Fully unlocked and compatible with any carrier of choice (e.g. AT&T, T-Mobile, Sprint, Verizon, US-Cellular, Cricket, Metro, etc.).\n",
                sellerRepository.findFirstByOrderByNameAsc()
        );
        List<Product> products = Arrays.asList(
                prod,
                new Product("iPhone", new Category("phone"), 1000, "Active", ".jpg",
                        "Fully unlocked and compatible with any carrier of choice (e.g. AT&T, T-Mobile, Sprint, Verizon, US-Cellular, Cricket, Metro, etc.).\n",
                        sellerRepository.findFirstByOrderByNameAsc()
                ),
                new Product("iPhone", new Category("phone"), 1000, "Active", ".jpg",
                        "Fully unlocked and compatible with any carrier of choice (e.g. AT&T, T-Mobile, Sprint, Verizon, US-Cellular, Cricket, Metro, etc.).\n",
                        sellerRepository.findFirstByOrderByNameAsc()
                ),
                new Product("iPhone", new Category("phone"), 1000, "Active", ".jpg",
                        "Fully unlocked and compatible with any carrier of choice (e.g. AT&T, T-Mobile, Sprint, Verizon, US-Cellular, Cricket, Metro, etc.).\n",
                        sellerRepository.findFirstByOrderByNameAsc()
                ),
                new Product("iPhone", new Category("phone"), 1000, "Active", ".jpg",
                        "Fully unlocked and compatible with any carrier of choice (e.g. AT&T, T-Mobile, Sprint, Verizon, US-Cellular, Cricket, Metro, etc.).\n",
                        sellerRepository.findFirstByOrderByNameAsc()
                )

        );
        model.addAttribute("products", products.toArray());
//        model.addAttribute("product", prod);
        return "index";
    }
}
