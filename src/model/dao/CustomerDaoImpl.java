package model.dao;

import model.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerDaoImpl implements CustomerDao{
    @Override
    public List<Customer> queryAllCustomers() {
        String sql = """
                SELECT * FROM "customer"
                """;
        try(
                Connection conn = connectionToDataBase();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                ){
            List<Customer> customerList = new ArrayList<>();

            while(rs.next()){
                customerList.add(new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getBoolean("is_deleted"),
                        rs.getDate("created_date")
                ));
            }
            return customerList;
        }catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public int deleteCustomerById(Integer id) {
        String sql = """
                DELETE FROM "customer" where id = ?
                """;
        try(
                Connection conn = connectionToDataBase();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setInt(1, id);
            int rowAffected = stmt.executeUpdate();
            return rowAffected;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int updateCustomerById(Integer id) {
        String sql = """
                UPDATE "customer"
                SET name=?, email=?
                WHERE id = ?
                """;
        try(
                Connection conn = connectionToDataBase();
                PreparedStatement pre = conn.prepareStatement(sql);
                ){
            Customer customer = searchCustomerById(id);
            if(customer == null){
                System.out.println("Customer not found!!");
            }
            else {
                System.out.print("[+] Insert customer name: ");
                pre.setString(1, new Scanner(System.in).nextLine());
                System.out.print("[+] Insert customer email: ");
                pre.setString(2, new Scanner(System.in).nextLine());
                pre.setInt(3, id);
                int rowAffected = pre.executeUpdate();
                return rowAffected;
            }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int addCustomer(Customer customer) {
        String sql = """
                INSERT INTO customer (name, email, password, is_deleted, created_date)
                VALUES (?, ?, ?, ?, ?)
                """;
        try(
                Connection connection = connectionToDataBase();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
                    preparedStatement.setString(1, customer.getName());
                    preparedStatement.setString(2, customer.getEmail());
                    preparedStatement.setString(3, customer.getPassword());
                    preparedStatement.setBoolean(4, customer.getIsDeleted());
                    preparedStatement.setDate(5, customer.getCreatedDate());
                    int rowAffected = preparedStatement.executeUpdate();
                    return rowAffected;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return 0;
    }

    @Override
    public Customer searchCustomerById(Integer id) {
        String sql = """
                SELECT * FROM "customer"
                WHERE id = ?
                """;
        try(
                Connection conn = connectionToDataBase();
                PreparedStatement pre = conn.prepareStatement(sql);

                ){
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            Customer customer = new Customer();
            while(rs.next()){
                customer = Customer.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .isDeleted(rs.getBoolean("is_deleted"))
                        .createdDate(rs.getDate("created_date"))
                        .build();
            }
            return customer;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public Connection connectionToDataBase() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5433/postgres",
                "postgres",
                "molika90&*");
    }
}
