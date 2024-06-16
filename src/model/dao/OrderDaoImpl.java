package model.dao;

import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;
import org.postgresql.util.OSUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderDaoImpl implements OrderDao {
    private final CustomerDao customerDao = new CustomerDaoImpl();
    @Override
    public int addNewOrder(Order order) {
        String sql = """
                INSERT INTO "order" (id, order_name, order_description, cus_id, ordered_at)
                VALUES (?, ?, ?, ?, ?)
                """;
        String sql1 = """
                INSERT INTO "product_order"
                VALUES(?,?)
                """;
        try(
                Connection connection = customerDao.connectionToDataBase();
                PreparedStatement pre = connection.prepareStatement(sql);
                PreparedStatement pre1 = connection.prepareStatement(sql1);
                ){
            pre.setInt(1, order.getId());
            pre.setString(2, order.getOrderName());
            pre.setString(3, order.getOrderDescription());
            pre.setInt(4, order.getCustomer().getId());
            pre.setDate(5, order.getOrderedAt());

            // product order
            for(Product product : order.getProductList()){
                pre1.setInt(1, product.getId());
                pre1.setInt(2, order.getId());
            }
            int rowAffected = pre.executeUpdate();
            int rowAffected1 = pre1.executeUpdate();
            return rowAffected;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int updateOrderById(Integer id) {
        String sql = """
                UPDATE "order"
                SET order_name=?, order_description=?
                WHERE id = ?
                """;
        try(
                Connection conn = customerDao.connectionToDataBase();
                PreparedStatement pre = conn.prepareStatement(sql);
        ){
            Order order = searchOrderById(id);
            if(order == null){
                System.out.println("Order not found");
            }
            else {
                System.out.print("[+] Insert new order name: ");
                pre.setString(1, new Scanner(System.in).nextLine());
                System.out.print("[+] Insert new order description: ");
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
    public int deleteOrderById(Integer id) {
        String sql = """
               DELETE FROM "order" WHERE id = ?
                """;
        try(
                Connection conn = customerDao.connectionToDataBase();
                PreparedStatement pre = conn.prepareStatement(sql);
                ){
            Order order = searchOrderById(id);
            String message = order == null ? "Cannot find order" : "Found order";
            System.out.println(message);
            if (order != null) {
                pre.setInt(1, id);
                int rowAffected = pre.executeUpdate();
                return rowAffected;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public Order searchOrderById(Integer id) {
        String sql = """
                SELECT * FROM "order" WHERE id = ?
                """;
        try(
                Connection conn = customerDao.connectionToDataBase();
                PreparedStatement pre = conn.prepareStatement(sql);
                ){
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            Order order = null;

            while(rs.next()){
                order = Order.builder()
                        .id(rs.getInt("id"))
                        .orderName(rs.getString("order_name"))
                        .orderDescription(rs.getString("order_description"))
                        .customer(Customer.builder()
                                .id(rs.getInt("id"))
                                .build())
                        .productList(new ArrayList<>())
                        .orderedAt(rs.getDate("ordered_at"))
                        .build();
            }
            return order;

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Order> queryAllOrders() {
        String sql = """
                SELECT * FROM "order"
                INNER JOIN "customer" c on c.id = "order".cus_id
                """;
        try(
                Connection connection = customerDao.connectionToDataBase();
                PreparedStatement pre = connection.prepareStatement(sql);
                ResultSet rs = pre.executeQuery();
                ){
            List<Order> orders = new ArrayList<>();

            while(rs.next()) {
                orders.add(Order.builder()
                                .id(rs.getInt("id"))
                                .orderName(rs.getString("order_name"))
                                .orderDescription(rs.getString("order_description"))
                                .orderedAt(rs.getDate("ordered_at"))
                                .customer(Customer.builder()
                                        .id(rs.getInt("cus_id"))
                                        .name(rs.getString("name"))
                                        .email(rs.getString("email"))
                                        .password(rs.getString("password"))
                                        .isDeleted(rs.getBoolean("is_deleted"))
                                        .createdDate(rs.getDate("created_date"))
                                        .build())
                        .build());
            }
            return orders;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }
}
