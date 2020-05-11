package edu.miu.cs545.group01.online.market.service;

import edu.miu.cs545.group01.online.market.domain.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCart addShoppingCart(ShoppingCart cart);

    ShoppingCart getShoppingCart(long id);

    List<ShoppingCart> getShoppingCarts();

    // void update(String cartId, ShoppingCart cart);

    void deleteShoppingCart(long id);
}





