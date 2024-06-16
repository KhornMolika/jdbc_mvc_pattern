package model.service;

import exception.ExceptionHandling;
import model.dto.CustomerDto;
import model.entity.Customer;

import java.util.List;

public interface CustomerService {
    int addCustomer(Customer customer) throws ExceptionHandling;
    int updateCustomer(Integer id) throws ExceptionHandling;
    int deleteCustomer(Integer id) throws ExceptionHandling;
    List<CustomerDto> queryAllCustomers() throws ExceptionHandling;
}
