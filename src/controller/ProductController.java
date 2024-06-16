package controller;

import exception.ExceptionHandling;
import model.entity.Product;
import model.service.ProductService;
import model.service.ProductServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ProductController {

    static ProductService productService = new ProductServiceImpl();
    static Scanner scanner = new Scanner(System.in);

    public static void addNewProduct() {
       try{
           System.out.println("========| Adding new product |=======");
           System.out.print("Enter product name: ");
           String name = scanner.nextLine();
           System.out.print("Enter product code: ");
           String code = scanner.nextLine();
           System.out.println("--------------------------------------");
           System.out.println("[*] Product Imported Date");
           System.out.print("Enter year (number): ");
           int importedYear = scanner.nextInt();
           System.out.print("Enter month (number): ");
           int importedMonth = scanner.nextInt();
           System.out.print("Enter day (number): ");
           int importedDay = scanner.nextInt();
           System.out.println("--------------------------------------");
           System.out.println("[*] Product Expired Date");
           System.out.print("Enter year (number): ");
           int expiredYear = scanner.nextInt();
           System.out.print("Enter month (number): ");
           int expiredMonth = scanner.nextInt();
           System.out.print("Enter day (number): ");
           int expiredDay = scanner.nextInt();
           scanner.nextLine();
           System.out.print("Enter product description: ");
           String description = scanner.nextLine();

           productService.addNewProduct(Product.builder()
                   .id(new Random().nextInt(10000))
                   .productName(name)
                   .productCode(code)
                   .isDeleted(false)
                   .importedDate(Date.valueOf(LocalDate.of(importedYear, importedMonth, importedDay)))
                   .expiredDate(Date.valueOf(LocalDate.of(expiredYear, expiredMonth, expiredDay)))
                   .productDescription(description)
                   .build());
       }catch (Exception e){
           System.out.println("Invalid input");
       }
    }

    public static List<Product> queryAllProducts() throws ExceptionHandling {
        return productService.queryAllProducts();
    }

    public static void deleteProduct() throws ExceptionHandling {
        System.out.println("========| Deleting product |=======");
        System.out.print("Enter product id: ");
        int id = scanner.nextInt();
        productService.deleteProduct(id);
    }

    public static void updateProduct() throws ExceptionHandling {
        try{
            System.out.println("========| Updating product |=======");
            System.out.print("Enter product id: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Product product = productService.getProductById(id);
            if(product == null){
                System.out.println("Product not found");
                return;
            }

            System.out.print("Enter product name: ");
            String productName = scanner.nextLine();
            if (!productName.trim().isEmpty()) {
                product.setProductName(productName);
            }

            System.out.print("Enter product code: ");
            String productCode = scanner.nextLine();
            if (!productCode.trim().isEmpty()) {
                product.setProductCode(productCode);
            }

            System.out.print("Enter product description: ");
            String productDescription = scanner.nextLine();
            if (!productDescription.trim().isEmpty()) {
                product.setProductDescription(productDescription);
            }

            productService.updateProduct(product);
            System.out.println("Product updated successfully");
        } catch (ExceptionHandling e) {
            System.out.println("Error: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Invalid input");
        }
    }
}
