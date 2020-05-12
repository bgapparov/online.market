package edu.miu.cs545.group01.online.market.service;

import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.Order;
import edu.miu.cs545.group01.online.market.domain.Seller;
import edu.miu.cs545.group01.online.market.domain.enums.OrderStatus;
import edu.miu.cs545.group01.online.market.exception.OrderStatusException;
import javassist.NotFoundException;

import java.util.List;

public interface OrderService {
    List<Order> getMyOrders(Buyer buyer);
    List<Order> getMyOrders(Seller seller);

    void cancelOrder(Buyer buyer, long orderId) throws NotFoundException, OrderStatusException;
    void setStatus(Seller seller, long orderId, OrderStatus orderStatus) throws NotFoundException, OrderStatusException;
}
