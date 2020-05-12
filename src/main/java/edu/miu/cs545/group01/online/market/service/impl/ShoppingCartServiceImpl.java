package edu.miu.cs545.group01.online.market.service.impl;

import edu.miu.cs545.group01.online.market.domain.ShoppingCart;
import edu.miu.cs545.group01.online.market.repository.ShoppingCartRepository;
import edu.miu.cs545.group01.online.market.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class ShoppingCartServiceImpl implements ShoppingCartService {

        @Autowired
        ShoppingCartRepository shoppingCartRepository;

        @Override
        public ShoppingCart addShoppingCart(ShoppingCart cart) {
            return shoppingCartRepository.save(cart);
        }

        @Override
        public ShoppingCart getShoppingCart(long id) {
            return null;
        }

        @Override
        public List<ShoppingCart> getShoppingCarts() {
            return shoppingCartRepository.findAll();
        }

        @Override
        public void deleteShoppingCart(long id) {
            shoppingCartRepository.deleteById(id);
        }
    }

