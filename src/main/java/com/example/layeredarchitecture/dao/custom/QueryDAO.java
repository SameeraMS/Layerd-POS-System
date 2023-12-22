package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.SuperDAO;
import com.example.layeredarchitecture.model.AddtblDto;
import com.example.layeredarchitecture.model.SearchDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
     ArrayList<SearchDto> search(String id) throws SQLException, ClassNotFoundException;

     ArrayList<AddtblDto> addtbl(String id) throws SQLException, ClassNotFoundException;
}
