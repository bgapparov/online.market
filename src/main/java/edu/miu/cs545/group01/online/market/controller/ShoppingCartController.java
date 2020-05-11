package edu.miu.cs545.group01.online.market.controller;


        import edu.miu.cs545.group01.online.market.domain.ShoppingCart;
        import edu.miu.cs545.group01.online.market.service.ShoppingCartService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.servlet.mvc.support.RedirectAttributes;

        import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shoppingcart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/")
    public String createShoppingCart(@ModelAttribute("items") ShoppingCart shoppingCart, Model model) {
        model.addAttribute("items", shoppingCart);
        return "shoppingcart/list";
    }

    @PostMapping("/add")
    public void saveShoppingCart(ShoppingCart shoppingCart) {
        shoppingCartService.addShoppingCart(shoppingCart);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteShoppingCart(@PathVariable("id") long id) {
        shoppingCartService.deleteShoppingCart(id);
    }


//    @PutMapping("/add/{productId}")
//    public void additem(@PathVariable String productId, HttpServletRequest request) {
//
//    }

}
