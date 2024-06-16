package model.service;

import exception.ExceptionHandling;
import model.dao.ProductDao;
import model.dao.ProductDaoImpl;
import model.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao = new ProductDaoImpl();

    @Override
    public int addNewProduct(Product product) throws ExceptionHandling {
        int result = productDao.addNewProduct(product);
        if(result > 0){
            System.out.println("Product added successfully");
            return 1;
        }
        else{
            throw new ExceptionHandling("Cannot add new product");
        }
    }

    @Override
    public Product getProductById(int id) throws ExceptionHandling {
        Product product = productDao.searchProductById(id);
        if (product == null) {
            throw new ExceptionHandling("Product not found");
        }
        return product;
    }

    @Override
    public void updateProduct(Product product) throws ExceptionHandling {
        int result = productDao.updateProduct(product);
        if (result <= 0) {
            throw new ExceptionHandling("Cannot update product");
        }
    }

    @Override
    public int deleteProduct(Integer id) throws ExceptionHandling {
        int result = productDao.deleteProduct(id);
        if (result > 0) {
            System.out.println("Product deleted successfully");
            return 1;
        } else {
            throw new ExceptionHandling("Cannot delete product");
        }
    }

    @Override
    public List<Product> queryAllProducts() {
        try{
            List<Product> products = productDao.queryAllProducts();
            if (products == null || products.isEmpty()) {
                throw new ExceptionHandling("No Products Data");
            }
            return products;
        }catch(ExceptionHandling e){
            System.err.println("Error while fetching products: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
