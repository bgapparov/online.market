package edu.miu.cs545.group01.online.market.service.impl;


import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.Order;
import edu.miu.cs545.group01.online.market.domain.Seller;
import edu.miu.cs545.group01.online.market.domain.enums.OrderStatus;
import edu.miu.cs545.group01.online.market.exception.OrderStatusException;
import edu.miu.cs545.group01.online.market.repository.OrderRepository;
import edu.miu.cs545.group01.online.market.service.OrderService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Override
    public List<Order> getMyOrders(Buyer buyer) {
        return orderRepository.findAllByBuyer(buyer);
    }
    @Override
    public List<Order> getMyOrders(Seller seller) {
        return orderRepository.findAllBySeller(seller);
    }

    @Override
    public Order getMyOrder(Buyer buyer, long orderId) {
        return orderRepository.findByBuyerAndId(buyer, orderId).orElse(null);
    }

    @Override
    public void cancelOrder(Buyer buyer, long orderId) throws NotFoundException, OrderStatusException {
        Order order = orderRepository.findByBuyerAndId(buyer, orderId).orElseThrow(()->new NotFoundException("Order is not found"));
        setOrderStatus(order, OrderStatus.CANCELED);
    }

    @Override
    public void setStatus(Seller seller, long orderId, OrderStatus orderStatus) throws NotFoundException, OrderStatusException {
        Order order = orderRepository.findBySellerAndId(seller, orderId).orElseThrow(()->new NotFoundException("Order is not found"));
        setOrderStatus(order, orderStatus);
    }

    private void setOrderStatus(Order order, OrderStatus status) {
        switch (status) {
            case CANCELED:
            case SHIPPED:
            if (order.getStatus() != OrderStatus.CREATED) {
                throw new OrderStatusException("Order status cannot be changed to " + status);
            }
            break;
            case DELIVERED:
                if (order.getStatus() != OrderStatus.SHIPPED) {
                    throw new OrderStatusException("Order status cannot be changed to " + status);
                }
            break;
            default:
                throw new OrderStatusException("Order status cannot be changed to " + status);
        }
        order.setStatus(status);
        order = orderRepository.save(order);

    }
}
