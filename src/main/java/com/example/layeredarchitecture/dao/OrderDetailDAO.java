package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface OrderDetailDAO extends CrudDAO<OrderDetailDTO> {
     boolean save(String orderId, OrderDetailDTO orderDetails ) throws SQLException, ClassNotFoundException;
}
