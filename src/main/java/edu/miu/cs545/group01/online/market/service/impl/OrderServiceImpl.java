package edu.miu.cs545.group01.online.market.service.impl;


import edu.miu.cs545.group01.online.market.domain.*;
import edu.miu.cs545.group01.online.market.domain.enums.GainPointType;
import edu.miu.cs545.group01.online.market.domain.enums.OrderStatus;
import edu.miu.cs545.group01.online.market.exception.OrderStatusException;
import edu.miu.cs545.group01.online.market.repository.OrderProductRepository;
import edu.miu.cs545.group01.online.market.repository.OrderRepository;
import edu.miu.cs545.group01.online.market.service.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    SellerService sellerService;
    @Autowired
    AddressService addressService;
    @Autowired
    BillingInfoService billingService;
    @Autowired
    GainPointService gainPointService;

    @Override
    public List<Order> getMyOrders(Buyer buyer) {
        return orderRepository.findAllByBuyer(buyer);
    }

    @Override
    public List<Order> getMyOrders(Seller seller) {
        return orderRepository.findAllBySeller(seller);
    }

    @Override
    public void cancelOrder(Buyer buyer, long orderId) throws NotFoundException, OrderStatusException {
        Order order = orderRepository.findByBuyerAndId(buyer, orderId).orElseThrow(() -> new NotFoundException("Order is not found"));
        float totalSpentPoints = 0;
        for (OrderProduct orderProduct : order.getOrderedProducts()) {
            totalSpentPoints += orderProduct.getPointPayment();
        }
        setOrderStatus(order, OrderStatus.CANCELED);
        if (totalSpentPoints > 0) {
            gainPointService.makePoints(GainPointType.REFUND, buyer, order, totalSpentPoints);
        }
    }

    @Override
    public void setStatus(Seller seller, long orderId, OrderStatus orderStatus) throws NotFoundException, OrderStatusException {
        Order order = orderRepository.findBySellerAndId(seller, orderId).orElseThrow(() -> new NotFoundException("Order is not found"));
        setOrderStatus(order, orderStatus);
        if (orderStatus == OrderStatus.DELIVERED) {
            float totalCashPayments = 0;
            for (OrderProduct orderProduct : order.getOrderedProducts()) {
                totalCashPayments += orderProduct.getCashPayment();
            }
            gainPointService.makePoints(GainPointType.EARN, order.getBuyer(), order, totalCashPayments / 100);
        }
    }

    @Override
    public List<Order> createOrder(CheckoutModel checkoutModel) throws NotFoundException {
        List<ShoppingCart> carts = shoppingCartService.getMyShoppingCarts(checkoutModel.getBuyer());
        Map<Long, List<ShoppingCart>> ordersBySellerId = new HashMap<>();
        for (ShoppingCart cart : carts) {
            long sellerId = cart.getProduct().getSeller().getId();
            if (ordersBySellerId.containsKey(sellerId)) {
                ordersBySellerId.get(sellerId).add(cart);
            } else {
                List<ShoppingCart> shcarts = new ArrayList<>();
                shcarts.add(cart);
                ordersBySellerId.put(sellerId, shcarts);
            }
        }
        List<Order> result = new ArrayList<>();
        Iterator<Map.Entry<Long, List<ShoppingCart>>> iterator = ordersBySellerId.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, List<ShoppingCart>> entry = iterator.next();
            result.add(createOrder(checkoutModel, carts.size(), entry.getKey(), entry.getValue()));
        }
        shoppingCartService.clearMyShoppingCart(checkoutModel.getBuyer());
        return result;
    }

    private Order createOrder(CheckoutModel checkoutModel, int cartsCount, Long sellerId, List<ShoppingCart> carts) throws NotFoundException {
        Seller seller = sellerService.getSellerById(sellerId);
        Address shippingAddress = addressService.getMyAddress(checkoutModel.getBuyer(), checkoutModel.getShippingAddressId());
        BillingInfo billingInfo = billingService.getBilling(checkoutModel.getBuyer(), checkoutModel.getBillingInfoId());
        Order order = new Order(new Date(), OrderStatus.CREATED, checkoutModel.getBuyer(), seller, shippingAddress, billingInfo, null, null, null);
        order = orderRepository.save(order);
        float totalCashPayments = 0;
        float totalSpentPoint = 0;
        for (ShoppingCart cart : carts) {
            float point = checkoutModel.getPointPayment() / cartsCount;
            totalSpentPoint += point;
            OrderProduct orderProduct = new OrderProduct(order, cart.getProduct(), cart.getQuantity(), point);
            totalCashPayments += orderProduct.getCashPayment();
            orderProduct = orderProductRepository.save(orderProduct);
        }
        if (totalSpentPoint > 0) {
            gainPointService.makePoints(GainPointType.SPEND, checkoutModel.getBuyer(), order, totalSpentPoint);
        }
//        pointService.earnPoints(checkoutModel.getBuyer(), order, totalCashPayments/100);//we will do it when status will be DELIVERED
        return order;
    }

    private void setOrderStatus(Order order, OrderStatus status) {
        switch (status) {
            case CANCELED:
                if (order.getStatus() != OrderStatus.CREATED) {
                    throw new OrderStatusException("Order status cannot be changed to " + status);
                }
                order.setCanceledDate(new Date());
                break;
            case SHIPPED:
                if (order.getStatus() != OrderStatus.CREATED) {
                    throw new OrderStatusException("Order status cannot be changed to " + status);
                }
                order.setShippingDate(new Date());
                break;
            case DELIVERED:
                if (order.getStatus() != OrderStatus.SHIPPED) {
                    throw new OrderStatusException("Order status cannot be changed to " + status);
                }
                order.setDeliveredDate(new Date());
                break;
            default:
                throw new OrderStatusException("Order status cannot be changed to " + status);
        }
        order.setStatus(status);
        order = orderRepository.save(order);

    }
}
