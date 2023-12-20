package com.example.layeredarchitecture.model;

import java.time.LocalDate;



public class OrderDTO  {
    private String orderId;
    private String orderDate;
    private String customerId;
    /*private String customerName;
    private BigDecimal orderTotal;*/




    public OrderDTO(String orderId, String orderDate, String customerId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerId = customerId;
        /*this.customerName = customerName;
        this.orderTotal = orderTotal;*/
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

   /* public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }*/

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId='" + orderId + '\'' +
                ", orderDate=" + orderDate +
                ", customerId='" + customerId + '\'' +
               /* ", customerName='" + customerName + '\'' +
                ", orderTotal=" + orderTotal +*/
                '}';
    }
}
