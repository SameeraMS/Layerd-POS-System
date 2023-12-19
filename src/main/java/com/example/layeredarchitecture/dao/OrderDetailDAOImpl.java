package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO{
    @Override
    public boolean saveOrderDetail(String orderId, OrderDetailDTO orderDetails ) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");


            stm.setString(1, orderId);
            stm.setString(2, orderDetails.getItemCode());
            stm.setBigDecimal(3, orderDetails.getUnitPrice());
            stm.setInt(4, orderDetails.getQty());


        return stm.executeUpdate() > 0;


    }
}
