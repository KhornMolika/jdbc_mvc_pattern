package controller;

import exception.ExceptionHandling;
import model.dao.CustomerDaoImpl;
import model.dto.CustomerDto;
import model.entity.Customer;
import model.service.CustomerService;
import model.service.CustomerServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CustomerController {
    private static final CustomerService customerService = new CustomerServiceImpl();
    static Scanner scanner = new Scanner(System.in);
    public static void addNewCustomer() throws ExceptionHandling {
        System.out.println("============| Add New Customer |===========");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        customerService.addCustomer(Customer.builder()
                        .id(new Random().nextInt(10000))
                        .name(name)
                        .email(email)
                        .password(password)
                        .isDeleted(false)
                        .createdDate(Date.valueOf(LocalDate.now()))
                .build());
    }
    public static List<CustomerDto> getAllCustomers() throws ExceptionHandling {
        System.out.println("============| Get All Customer |===========");
        return customerService.queryAllCustomers();
    }
    public static void deleteCustomer() throws ExceptionHandling {
        System.out.println("============| Delete Customer |===========");
        System.out.print("Enter id: ");
        int id = scanner.nextInt();
        customerService.deleteCustomer(id);
    }
    public static void updateCustomer() throws ExceptionHandling {
        System.out.println("============| Update Customer |===========");
        System.out.print("Enter id: ");
        int id = scanner.nextInt();
        customerService.updateCustomer(id);
    }
}
