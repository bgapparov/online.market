package edu.miu.cs545.group01.online.market.service;

import edu.miu.cs545.group01.online.market.domain.*;
import edu.miu.cs545.group01.online.market.domain.enums.OrderStatus;
import edu.miu.cs545.group01.online.market.exception.OrderStatusException;
import javassist.NotFoundException;

import java.util.List;

public interface OrderService {
    List<Order> getMyOrders(Buyer buyer);
    List<Order> getMyOrders(Seller seller);
    Order getOrder(Buyer buyer, long orderId) throws NotFoundException;
    OrderProduct getOrderProduct(Buyer buyer, long orderProductId) throws NotFoundException;

    void cancelOrder(Buyer buyer, long orderId) throws NotFoundException, OrderStatusException;
    void setStatus(Seller seller, long orderId, OrderStatus orderStatus) throws NotFoundException, OrderStatusException;

    List<Order> createOrder(CheckoutModel checkoutModel) throws NotFoundException;

    Review leaveReview(Buyer buyer, long orderProductId, ReviewModel reviewModel) throws NotFoundException;
}
