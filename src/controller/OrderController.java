package controller;

import exception.ExceptionHandling;
import model.dto.OrderDto;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;
import model.service.OrderService;
import model.service.OrderServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderController {
    private static final OrderService orderService = new OrderServiceImpl();

    public static List<OrderDto> queryAllOrders() throws ExceptionHandling {
        System.out.println("============| Query All Orders ============");
        return orderService.queryAllOrders();
    }

    public static void addNewOrder() throws ExceptionHandling {
        System.out.println("===========| Add New Order |===========");
        System.out.print("Enter order name: ");
        String orderName = new Scanner(System.in).nextLine();
        System.out.print("Enter order description: ");
        String orderDescription = new Scanner(System.in).nextLine();
        System.out.print("Enter Customer ID: ");
        int customerId = new Scanner(System.in).nextInt();
        System.out.print("Enter Product ID: ");
        int productId = new Scanner(System.in).nextInt();
        new OrderServiceImpl().addNewOrder(Order.builder()
                .id(1)
                .orderName(orderName)
                .orderDescription(orderDescription)
                .orderedAt(Date.valueOf(LocalDate.now()))
                .customer(Customer.builder()
                        .id(customerId)
                        .build())
                .productList(new ArrayList<>(
                        List.of(Product.builder()
                                .id(productId)
                                .build())
                ))
                .build());
    }

    public static void deleteOrder(){
        System.out.println("===========| Delete Order ============");
        System.out.print("Enter order ID: ");
        new OrderServiceImpl().deleteOrderById(new Scanner(System.in).nextInt());
    }

    public static void updateOrder() throws ExceptionHandling {
        System.out.println("===========| Update Order ============");
        System.out.print("Enter order ID: ");
        new OrderServiceImpl().updateOrderById(new Scanner(System.in).nextInt());
    }
}
