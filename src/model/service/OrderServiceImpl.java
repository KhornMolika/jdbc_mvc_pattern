package model.service;

import exception.ExceptionHandling;
import mapper.OrderMapper;
import model.dto.OrderDto;
import model.entity.Order;
import model.dao.OrderDao;
import model.dao.OrderDaoImpl;
import java.util.List;

public class OrderServiceImpl implements OrderService{

    private final OrderDao orderDao = new OrderDaoImpl();

    @Override
    public List<OrderDto> queryAllOrders() throws ExceptionHandling {
        try {
            List<Order> orders = orderDao.queryAllOrders();
            if(!(orders.isEmpty())){
                return orderDao.queryAllOrders().stream().map(OrderMapper::mapOrderToOrderDto).toList();
            }else {
                throw new ExceptionHandling("No Order Data !");
            }
        }catch (ExceptionHandling e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void addNewOrder(Order order) throws ExceptionHandling {
        try{
            if (orderDao.addNewOrder(order) > 0) {
                throw new ExceptionHandling("Order Added Successfully !");
            }else{
                throw new ExceptionHandling("Cant add order");
            }
        }catch (ExceptionHandling e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateOrderById(Integer id) throws ExceptionHandling {
        try {
            if(orderDao.updateOrderById(id)>0) {
                throw new ExceptionHandling("Order Updated Successfully !");
            }else {
                throw new ExceptionHandling("Cant update order");
            }
        }catch (ExceptionHandling e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteOrderById(Integer id) {
        try {
            if(orderDao.deleteOrderById(id)>0) {
                throw new ExceptionHandling("Order Deleted Successfully !");
            }else {
                throw new ExceptionHandling("Cant delete order");
            }
        }catch (ExceptionHandling e){
            System.out.println(e.getMessage());
        }
    }
}
