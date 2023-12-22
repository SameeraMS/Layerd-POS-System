package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.dto.ItemDTO;
import com.example.layeredarchitecture.entity.Item;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();*/

        ResultSet rst = SQLUtil.execute("SELECT * FROM Item");

        ArrayList<Item> getallItem = new ArrayList<>();

        while (rst.next()) {
            Item item = new Item(rst.getString("code"), rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
            getallItem.add(item);
        }

        return getallItem;
    }
    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {

        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");
        pstm.setString(1, code);
        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("DELETE FROM Item WHERE code=?",code);
    }
    @Override
    public boolean save(Item item) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");
        pstm.setString(1, itemDTO.getCode());
        pstm.setString(2, itemDTO.getDescription());
        pstm.setBigDecimal(3, itemDTO.getUnitPrice());
        pstm.setInt(4, itemDTO.getQtyOnHand());
        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",
                item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());
    }
    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
        pstm.setString(1, itemDTO.getDescription());
        pstm.setBigDecimal(2, itemDTO.getUnitPrice());
        pstm.setInt(3, itemDTO.getQtyOnHand());
        pstm.setString(4, itemDTO.getCode());
        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",
                item.getDescription(),item.getUnitPrice(),item.getQtyOnHand(),item.getCode());
    }
    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
        pstm.setString(1, code);
        return pstm.executeQuery().next();*/

        ResultSet set = SQLUtil.execute("SELECT code FROM Item WHERE code=?",code);
        return set.next();

    }
    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
      //  Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = SQLUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }
    @Override
    public Item search(String newItemCode) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
        pstm.setString(1, newItemCode + "");
        ResultSet rst = pstm.executeQuery();*/

        ResultSet rst = SQLUtil.execute("SELECT * FROM Item WHERE code=?",newItemCode + "");
        rst.next();
        Item item = new Item(newItemCode + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));

        return item;
    }




}
