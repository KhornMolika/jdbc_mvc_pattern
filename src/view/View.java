package view;

import controller.CustomerController;
import controller.OrderController;
import controller.ProductController;
import exception.ExceptionHandling;

import java.util.InputMismatchException;
import java.util.Scanner;

public class View {

    public static void view() throws ExceptionHandling {
        while (true) {
           try{
               System.out.println("=========| Main Menu |=========");
               System.out.println("1. Customer menu");
               System.out.println("2. Order menu");
               System.out.println("3. Product menu");
               System.out.println("0. Exit");
               System.out.println("============================");
               System.out.print("Enter your choice: ");
               int op = new Scanner(System.in).nextInt();
               switch (op) {
                   case 1 ->{
                       while (true) {
                           try{
                               System.out.println("=========| Customer Menu |=========");
                               System.out.println("1. Add New Customer");
                               System.out.println("2. View All Customers");
                               System.out.println("3. Delete Customer By ID");
                               System.out.println("4. Update Customer By ID");
                               System.out.println("0. Back");
                               System.out.println("======================================");
                               System.out.print("Enter your choice: ");
                               int op2 = new Scanner(System.in).nextInt();
                               switch (op2) {
                                   case 1 -> CustomerController.addNewCustomer();
                                   case 2 -> CustomerController.getAllCustomers().forEach(System.out::println);
                                   case 3 -> CustomerController.deleteCustomer();
                                   case 4 -> CustomerController.updateCustomer();
                                   default -> System.out.println("Invalid choice!!!!");
                               }
                               if (op2 == 0) break;
                           }catch (InputMismatchException e){
                               System.out.println("Invalid choice!!!!");
                           }
                       }
                   }
                   case 2 ->{
                       while (true) {
                           try{
                               System.out.println("=========| Order Menu |=========");
                               System.out.println("1. Add New Order");
                               System.out.println("2. View All Orders");
                               System.out.println("3. Delete Order By ID");
                               System.out.println("4. Update Order By ID");
                               System.out.println("0. Exit");
                               System.out.println("=====================================");
                               System.out.print("Enter your choice: ");
                               int op3 = new Scanner(System.in).nextInt();
                               switch (op3) {
                                   case 1 -> OrderController.addNewOrder();
                                   case 2 -> OrderController.queryAllOrders().forEach(System.out::println);
                                   case 3 -> OrderController.deleteOrder();
                                   case 4 -> OrderController.updateOrder();
                                   default -> System.out.println("Invalid choice!!!!");
                               }
                               if(op3 == 0) break;
                           }catch (InputMismatchException e){
                               System.out.println("Invalid choice!!!!");
                           }
                       }
                   }
                   case 3 ->{
                       while (true) {
                           try{
                               System.out.println("=========| Product Menu |=========");
                               System.out.println("1. Add New Product");
                               System.out.println("2. View All Products");
                               System.out.println("3. Delete Product By ID");
                               System.out.println("4. Update Product By ID");
                               System.out.println("0. Back");
                               System.out.println("=====================================");
                               System.out.print("Enter your choice: ");
                               int op4 = new Scanner(System.in).nextInt();
                               switch (op4) {
                                   case 1 -> ProductController.addNewProduct();
                                   case 2 -> ProductController.queryAllProducts().forEach(System.out::println);
                                   case 3 -> ProductController.deleteProduct();
                                   case 4 -> ProductController.updateProduct();
                                   default -> System.out.println("Invalid choice!!!!");
                               }
                               if(op4 == 0) break;
                           }catch (InputMismatchException e){
                               System.out.println("Invalid choice!!!!");
                           }
                       }
                   }
                   case 0 -> System.exit(0);
                   default -> System.out.println("Invalid option!!!!");
               }
           }catch (InputMismatchException e){
               System.out.println("Please enter a valid number.");
           }
        }
    }
}
