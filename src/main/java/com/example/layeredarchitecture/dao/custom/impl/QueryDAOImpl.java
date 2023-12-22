package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.QueryDAO;
import com.example.layeredarchitecture.dto.AddtblDto;
import com.example.layeredarchitecture.dto.SearchDto;

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

    public ArrayList<AddtblDto> addtbl(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select od.oid, o.date, od.itemCode, i.description, od.qty, od.unitPrice from OrderDetails od join Item i on od.itemCode = i.code join Orders o on od.oid = o.oid where o.oid = ?",id);

        ArrayList<AddtblDto> getAlldetails = new ArrayList<>();

        while (rst.next()) {
            AddtblDto addtblDto = new AddtblDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6));
            getAlldetails.add(addtblDto);
        }

        return getAlldetails;
    }
}
