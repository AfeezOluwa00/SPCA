package com.example.spca.model;

import java.io.Serializable;

public class BasketItem implements Serializable {
    private String stockItemId;
    private String title;
    private String manufacturer;
    private String price;
    private String quantity,  comments;

    private float ratings;
    public BasketItem() {
        // Default constructor required for Firebase
    }

    public BasketItem(String stockItemId, String title, String manufacturer, String price, String quantity) {
        this.stockItemId = stockItemId;
        this.title = title;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and setters
    public String getStockItemId() {
        return stockItemId;
    }

    public void setStockItemId(String stockItemId) {
        this.stockItemId = stockItemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setTimestamp(long timestamp) {
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments){
}

    public float getRatings() {
        return ratings;
    }




    public void setRating(float rating) {
    }
}
