package edu.miu.cs545.group01.online.market.service;

import edu.miu.cs545.group01.online.market.domain.ShoppingCart;
import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.ShoppingCart;
import javassist.NotFoundException;

import java.util.List;

public interface ShoppingCartService {

    List<ShoppingCart> getMyShoppingCarts(Buyer buyer);
    ShoppingCart addShoppingCart(Buyer buyer, long prorductId) throws NotFoundException;

    ShoppingCart deleteShoppingCart(long buyerId, long id);

    void setQuantity(long buyerId, long cartId, int quantity) throws NotFoundException;
    void clearMyShoppingCart(Buyer buyer);
}





