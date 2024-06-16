package model.service;

import exception.ExceptionHandling;
import model.dto.OrderDto;
import model.entity.Order;

import java.util.List;

public interface OrderService {
    List<OrderDto> queryAllOrders() throws ExceptionHandling;
    void addNewOrder(Order order) throws ExceptionHandling;
    void updateOrderById(Integer id) throws ExceptionHandling;
    void deleteOrderById(Integer id);
}
