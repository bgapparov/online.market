package edu.miu.cs545.group01.online.market.service.impl;

import edu.miu.cs545.group01.online.market.domain.ShoppingCart;
import edu.miu.cs545.group01.online.market.repository.ShoppingCartRepository;
import edu.miu.cs545.group01.online.market.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.Product;
import edu.miu.cs545.group01.online.market.service.ProductService;
import javassist.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ProductService productService;

    @Override
    public List<ShoppingCart> getMyShoppingCarts(Buyer buyer) {
        return shoppingCartRepository.findAllByBuyer(buyer);
    }

    @Override
    public ShoppingCart addShoppingCart(Buyer buyer, long prorductId) throws NotFoundException {
        Product product = productService.getProduct(prorductId);
        ShoppingCart shoppingCart = shoppingCartRepository.findByBuyerAndProduct(buyer, product).orElse(null);
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart(buyer, product, 1, new Date());
        } else {
            shoppingCart.setQuantity(shoppingCart.getQuantity() + 1);
        }
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart deleteShoppingCart(long buyerId, long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElse(null);
        if (shoppingCart == null) {
            return null;
        }

        if (shoppingCart.getBuyer().getId() != buyerId) {
            throw new RuntimeException("Shopping cart cannot be removed");
        }
        shoppingCartRepository.delete(shoppingCart);
        return shoppingCart;
    }

    @Override
    public void setQuantity(long buyerId, long cartId, int quantity) throws NotFoundException {
        ShoppingCart cart = shoppingCartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("Shopping cart item is not found"));
        if (cart.getBuyer().getId() != buyerId) {
            throw new NotFoundException("Shopping cart item is not found");
        }
        cart.setQuantity(quantity);
        shoppingCartRepository.save(cart);
    }

    @Transactional
    public void clearMyShoppingCart(Buyer buyer) {
        shoppingCartRepository.deleteAllByBuyer(buyer);
    }
}

