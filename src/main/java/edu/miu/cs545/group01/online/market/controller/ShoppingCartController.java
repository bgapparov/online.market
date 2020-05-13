package edu.miu.cs545.group01.online.market.controller;
        import edu.miu.cs545.group01.online.market.service.ShoppingCartService;
        import org.springframework.beans.factory.annotation.Autowired;
        import javassist.NotFoundException;
        import org.springframework.http.HttpStatus;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/buyer/cart")
public class ShoppingCartController extends BaseController{
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/list")
    public String myShoppingCart(Model model) {
        model.addAttribute("items", shoppingCartService.getMyShoppingCarts(getCurrentBuyer()));
        return "buyer/cart/list";
    }

    @PostMapping("/add/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addToShoppingCart(@PathVariable("productId") long productId) throws NotFoundException {
        shoppingCartService.addShoppingCart(getCurrentBuyer(), productId );
    }
    @DeleteMapping("/delete/{cartId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShoppingCart(@PathVariable("cartId") long id) {
        shoppingCartService.deleteShoppingCart(getCurrentBuyer().getId(), id);
    }

    @PostMapping("/set-quantity/{cartId}/{quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setQuantity(@PathVariable("cartId") long cartId, @PathVariable("quantity") int quantity) throws NotFoundException {
        shoppingCartService.setQuantity(getCurrentBuyer().getId(), cartId, quantity);
    }
}
