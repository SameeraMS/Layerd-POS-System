package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.QueryDAO;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.SearchDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    public ArrayList<SearchDto> search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select c.name, o.oid, o.date from Customer c join Orders o on c.id = o.customerID where o.customerID = ?",id);

        ArrayList<SearchDto> getAllsearch = new ArrayList<>();

        while (rst.next()) {
            SearchDto searchDto = new SearchDto(rst.getString(1), rst.getString(2), rst.getString(3));
            getAllsearch.add(searchDto);
        }

        return getAllsearch;
    }
}
