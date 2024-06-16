import controller.CustomerController;
import controller.OrderController;
import controller.ProductController;
import exception.ExceptionHandling;
import model.dao.*;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;
import model.service.CustomerServiceImpl;
import view.View;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws ExceptionHandling {

        View.view();

//        new OrderController().updateOrder();
//        new OrderController().queryAllOrders().forEach(System.out::println);
//        Product product = new ProductDaoImpl().searchProductById(1);
//        System.out.println(product);
//        new ProductController().updateProduct();
//        new ProductController().queryAllProducts().forEach(System.out::println);
//        new CustomerController().updateCustomer();
//        new CustomerController().addNewCustomer();
//        new OrderDaoImpl().addNewOrder(Order.builder()
//                        .id(new Random().nextInt(1000))
//                        .orderName("my order")
//                        .orderDescription("a cup of coffee")
//                        .customer(Customer.builder()
//                                .id(4)
//                                .build())
//                        .orderedAt(Date.valueOf(LocalDate.now()))
//                        .productList(new ArrayList<>(List.of(Product.builder()
//                                .id(1)
//                                .build())))
//                .build());
//
//        new OrderDaoImpl().queryAllOrders().forEach(System.out::println);
//        new OrderDaoImpl().deleteOrderById(1);
//        new OrderDaoImpl().updateOrderById(2);
//
//                new ProductDaoImpl().addNewProduct(new Product(
//                1,
//                "Fanta",
//                "112",
//                false,
//                Date.valueOf(LocalDate.of(2024, 9, 9)),
//                Date.valueOf(LocalDate.of(2025, 1,1)),
//                "Energy drink"
//        ));
//
//        new ProductDaoImpl().addNewProduct(Product.builder()
//                        .id(3)
//                        .productName("String")
//                        .productCode("003")
//                        .isDeleted(false)
//                        .importedDate(Date.valueOf(LocalDate.of(2023, 1,10)))
//                        .expiredDate(Date.valueOf(LocalDate.of(2024, 2, 1)))
//                        .productDescription("Import From China")
//                .build());
//        new ProductDaoImpl().queryAllProducts().forEach(System.out::println);
//        Product product = new ProductDaoImpl().searchProductById(3);
//        System.out.println(product);
//        new ProductDaoImpl().updateProduct(2);
    }
}