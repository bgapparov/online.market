package edu.miu.cs545.group01.online.market.controller;

import edu.miu.cs545.group01.online.market.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
    @Autowired
    SellerService sellerService;
    @GetMapping("/pending-sellers")
    public String pendingSellers(Model model){
        model.addAttribute("pendingSellers", sellerService.getPendingSellers());
        return "admin/pending-sellers";
    }
}
