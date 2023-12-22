package com.example.layeredarchitecture.bo.custom.impl;

import com.example.layeredarchitecture.bo.custom.PlaceOrderBO;
import com.example.layeredarchitecture.dao.DAOFactory;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.dao.custom.OrderDAO;
import com.example.layeredarchitecture.dao.custom.OrderDetailDAO;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.dto.CustomerDTO;
import com.example.layeredarchitecture.dto.ItemDTO;
import com.example.layeredarchitecture.dto.OrderDetailDTO;
import com.example.layeredarchitecture.entity.Customer;
import com.example.layeredarchitecture.entity.Item;


import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailDAO orderDetailsDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);
    @Override
    public boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        /*Transaction*/
        Connection connection = null;
            connection = DBConnection.getDbConnection().getConnection();
            boolean isExist = orderDAO.exist(orderId);

            if (isExist) {
                return false;
            }

            connection.setAutoCommit(false);

            boolean isSaved = orderDAO.save(orderId, orderDate, customerId);

            if (!isSaved) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            for (OrderDetailDTO detail : orderDetails) {

                boolean isOk = orderDetailsDAO.save(orderId, detail);

                if (!isOk) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }


//                //Search & Update Item
                ItemDTO item = findItem(detail.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

                boolean isUpdateQty = itemDAO.update(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));

                if (!(isUpdateQty)) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }

            connection.commit();
            connection.setAutoCommit(true);
            return true;
    }
    @Override
    public ItemDTO findItem(String code) throws SQLException, ClassNotFoundException {
            Item item = itemDAO.search(code);
            return new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand());
    }
    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
         Customer customer = customerDAO.search(id);
         return new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress());
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException{
        return itemDAO.exist(code);
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
            return orderDAO.nextId();
    }
    @Override
    public ArrayList<CustomerDTO> loadAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();

        for (Customer customer : allCustomers) {
            customerDTOS.add(new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress()));
        }

        return customerDTOS;
    }

    @Override
    public ArrayList<ItemDTO> loadAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> allitems = itemDAO.getAll();
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();

        for (Item item : allitems) {
            itemDTOS.add(new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
        }

        return itemDTOS;
    }
}
