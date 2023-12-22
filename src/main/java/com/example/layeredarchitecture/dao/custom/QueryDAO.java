package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.SuperDAO;
import com.example.layeredarchitecture.dto.AddtblDto;
import com.example.layeredarchitecture.dto.SearchDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
     ArrayList<SearchDto> search(String id) throws SQLException, ClassNotFoundException;

     ArrayList<AddtblDto> addtbl(String id) throws SQLException, ClassNotFoundException;
}
