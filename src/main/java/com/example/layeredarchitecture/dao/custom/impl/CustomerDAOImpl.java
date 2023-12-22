package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dto.CustomerDTO;
import com.example.layeredarchitecture.entity.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");

        ArrayList<Customer> getAllCustomer = new ArrayList<>();

        while (rst.next()) {
            Customer entity = new Customer(rst.getString("id"), rst.getString("name"), rst.getString("address"));
            getAllCustomer.add(entity);
        }

        return getAllCustomer;
    }
    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");
        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",
                entity.getId(),entity.getName(),entity.getAddress());

    }
    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getId());
        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",
                entity.getName(),entity.getAddress(),entity.getId());
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
        pstm.setString(1, id);
        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("DELETE FROM Customer WHERE id=?",
                id);
    }
    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
        pstm.setString(1, id);
        return pstm.executeQuery().next();*/

        ResultSet set = SQLUtil.execute("SELECT id FROM Customer WHERE id=?",
                id);
        return set.next();
    }
    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
       // Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = SQLUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }
    @Override
    public Customer search(String newValue) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id=?");
        pstm.setString(1, newValue + "");
        ResultSet rst = pstm.executeQuery();*/

        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE id=?",newValue + "");

        rst.next();
        Customer entity = new Customer(newValue + "", rst.getString("name"), rst.getString("address"));

        return entity;
    }
}
