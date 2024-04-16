package com.example.spca.model;

public class BasketItem {
    private String stockItemId;
    private String title;
    private String manufacturer;
    private String price;
    private String quantity;

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
}
