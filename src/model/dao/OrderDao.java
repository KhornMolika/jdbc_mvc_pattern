package model.dao;

import model.entity.Order;

import java.util.List;

public interface OrderDao {
    int addNewOrder(Order order);
    int updateOrderById(Integer id);
    int deleteOrderById(Integer id);
    Order searchOrderById(Integer id);
    List<Order> queryAllOrders();
}
