package model.service;

import exception.ExceptionHandling;
import model.entity.Product;

import java.util.List;

public interface ProductService {
    int addNewProduct(Product product) throws ExceptionHandling;
    Product getProductById(int id) throws ExceptionHandling;
    void updateProduct(Product product) throws ExceptionHandling;
    int deleteProduct(Integer id) throws ExceptionHandling;
    List<Product> queryAllProducts() throws ExceptionHandling;
}
