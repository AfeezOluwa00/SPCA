package com.example.spca.model;

public class BasketItemBuilder {
    private String stockItemId;
    private String title;
    private String manufacturer;
    private String price;
    private String quantity;
    private String comments = "";
    private float ratings = 0.0f;

    // Constructor with required parameters
    public BasketItemBuilder(String stockItemId, String title, String manufacturer, String price, String quantity) {
        this.stockItemId = stockItemId;
        this.title = title;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
    }

    // Setter methods for optional parameters
    public BasketItemBuilder comments(String comments) {
        this.comments = comments;
        return this;
    }

    public BasketItemBuilder ratings(float ratings) {
        this.ratings = ratings;
        return this;
    }

    // Method to build the BasketItem object
    public BasketItem build() {
        return new BasketItem(stockItemId, title, manufacturer, price, quantity, comments, ratings);
    }
}
