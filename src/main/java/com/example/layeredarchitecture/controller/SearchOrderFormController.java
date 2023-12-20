package com.example.layeredarchitecture.controller;

import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dao.custom.OrderDAO;
import com.example.layeredarchitecture.dao.custom.impl.CustomerDAOImpl;
import com.example.layeredarchitecture.dao.custom.impl.OrderDAOImpl;
import com.example.layeredarchitecture.dao.custom.impl.OrderDetailDAOImpl;
import com.example.layeredarchitecture.dao.custom.impl.QueryDAOImpl;
import com.example.layeredarchitecture.model.*;
import com.example.layeredarchitecture.view.tdm.SearchOrderTM;
import com.jfoenix.controls.JFXComboBox;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchOrderFormController {

    public AnchorPane root;
    public TextField txtCustomerName;
    public JFXComboBox<String> cmbOrderId;
    public TableView<SearchOrderTM> tblOrderDetails;
    public Label lblId;
    public Label lblDate;
    public TextField txtOrderDate;
    public JFXComboBox<String> cmbCustomerId;

    CustomerDAO customerDAO = new CustomerDAOImpl();
    OrderDAOImpl orderDAO = new OrderDAOImpl();
    OrderDetailDAOImpl orderDetailsDAO = new OrderDetailDAOImpl();

    QueryDAOImpl queryDAO = new QueryDAOImpl();


    public void initialize() {
        loadallcustomerIds();
    }

    private void loadallcustomerIds() {
        try {

            ArrayList<CustomerDTO> allCustomers = customerDAO.getAll();

            for (CustomerDTO c : allCustomers) {
                cmbCustomerId.getItems().add(c.getId());
            }



        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load customer ids").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void navigateToHome(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/com/example/layeredarchitecture/main-form.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    public void OrderIdOnAction(ActionEvent actionEvent){
        String id = (String) cmbOrderId.getValue();

        try {

            tblOrderDetails.getItems().clear();
            ArrayList<AddtblDto> dtolist = queryDAO.addtbl(id);

            for (AddtblDto c : dtolist) {
                lblId.setText(c.getOid());
                txtOrderDate.setText(c.getDate());

                tblOrderDetails.getItems().add(new SearchOrderTM(c.getItemcode(), c.getDescription(), c.getQty(), c.getUnitprice()));
            }

            tblOrderDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
            tblOrderDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
            tblOrderDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
            tblOrderDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void cusIdOnAction(ActionEvent actionEvent) {

        String id = (String) cmbCustomerId.getValue();
        cmbOrderId.getItems().clear();

        try {
            ArrayList<SearchDto> dtolist = queryDAO.search(id);

            for (SearchDto c : dtolist) {
                cmbOrderId.getItems().add(c.getOid());
                txtCustomerName.setText(c.getName());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
