package com.example.layeredarchitecture.view.tdm;

import java.math.BigDecimal;

public class SearchOrderTM {
    private String code;
    private String description;
    private String qty;
    private String unitPrice;

    private double total;

    public SearchOrderTM() {
    }

    public SearchOrderTM(String code, String description, String qty, String unitPrice, double total) {
        this.code = code;
        this.description = description;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.total = total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "SearchOrderTM{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", qty='" + qty + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", total=" + total +
                '}';
    }
}
