package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.OrderDAO;
import com.example.layeredarchitecture.dto.OrderDTO;
import com.example.layeredarchitecture.entity.Order;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();*/

        ResultSet rst = SQLUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");

        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    @Override
    public Order search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Orders WHERE oid=?",newValue + "");

        rst.next();
        Order order = new Order(newValue + "", rst.getString("date"), rst.getString("customerID"));

        return order;
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Orders");

        ArrayList<Order> getAllOrders = new ArrayList<>();

        while (rst.next()) {
            Order order = new Order(rst.getString("oid"), rst.getString("date"), rst.getString("customerID"));
            getAllOrders.add(order);
        }

        return getAllOrders;
    }

    @Override
    public boolean save(Order dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Order dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String orderId ) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
        stm.setString(1, orderId);*/

        ResultSet rst = SQLUtil.execute("SELECT oid FROM `Orders` WHERE oid=?",orderId);
        return rst.next();



    }
    @Override
    public boolean save(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
        stm.setString(1, orderId);
        stm.setDate(2, Date.valueOf(orderDate));
        stm.setString(3, customerId);

        return stm.executeUpdate() > 0;*/

        return SQLUtil.execute("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)",
                orderId,orderDate,customerId);

    }
}
