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
    }
}