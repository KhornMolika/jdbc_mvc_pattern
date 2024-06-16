package model.service;

import exception.ExceptionHandling;
import mapper.CustomerMapper;
import model.dao.CustomerDao;
import model.dao.CustomerDaoImpl;
import model.dto.CustomerDto;
import model.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerDao customerDao= new CustomerDaoImpl();

    @Override
    public int addCustomer(Customer customer) throws ExceptionHandling {
        int result = customerDao.addCustomer(customer);
        if(result > 0){
            System.out.println("Successfully added customer");
            return 1;
        }else {
            throw new ExceptionHandling("Cannot add customer");
        }
    }

    @Override
    public int updateCustomer(Integer id) throws ExceptionHandling {
        int result = customerDao.updateCustomerById(id);
        if(result>0){
            System.out.println("Successfully updated customer");
            return 1;
        }
        else{
            throw new ExceptionHandling("Cannot update customer");
        }
    }

    @Override
    public int deleteCustomer(Integer id) throws ExceptionHandling {
        int result = customerDao.deleteCustomerById(id);
        if(result > 0){
            System.out.println("Successfully deleted customer");
            return 1;
        }
        else{
            throw new ExceptionHandling("Cannot delete customer");
        }
    }

    @Override
    public List<CustomerDto> queryAllCustomers() throws ExceptionHandling {
        try {
            List<Customer> customers = customerDao.queryAllCustomers();
            if(customers.isEmpty() || customers == null){
                throw new ExceptionHandling("No Customer Data !");
            }
            return customerDao.queryAllCustomers().stream().map(CustomerMapper::fromCustomerToCustomerDto).toList();
        }catch (ExceptionHandling e){
            System.err.println("Error while fetching customers: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
