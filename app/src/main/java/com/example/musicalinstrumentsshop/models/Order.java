package com.example.musicalinstrumentsshop.models;

import java.io.Serializable;
import java.sql.Time;

public class Order implements Serializable {

    private String userName;
    private int quantity;
    private double price;
    private String itemName;
    private Time time;

    public Order(String userName, int quantity, double price, String itemName) {
        this.userName = userName;
        this.quantity = quantity;
        this.price = price;
        this.itemName = itemName;
    }

    public Order(){

    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getItemName() {
        return itemName;
    }

    public Time getTime() {
        return time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Order details = " +
                "Customer: " + userName + " | " +
                "quantity: " + quantity + " | " +
                "price: " + price + " | " +
                "item: " + itemName + " | " +
                "time: " + time;
    }
}
