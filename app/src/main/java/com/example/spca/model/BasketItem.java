package com.example.spca.model;

import java.io.Serializable;

public class BasketItem implements Serializable {
    private String stockItemId;
    private String title;
    private String manufacturer;
    private String price;
    private String quantity;
    private String comments;
    private float ratings;

    // Public no-argument constructor required by Firebase for deserialization
    public BasketItem() {
        // Default constructor required for Firebase deserialization
    }

    // Constructor with all fields
    public BasketItem(String stockItemId, String title, String manufacturer, String price, String quantity, String comments, float ratings) {
        this.stockItemId = stockItemId;
        this.title = title;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
        this.comments = comments;
        this.ratings = ratings;
    }

    // Getters and setters for all fields
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public float getRatings() {
        return ratings;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }

    // toString(), equals(), and hashCode() methods
    @Override
    public String toString() {
        return "BasketItem{" +
                "stockItemId='" + stockItemId + '\'' +
                ", title='" + title + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", price='" + price + '\'' +
                ", quantity='" + quantity + '\'' +
                ", comments='" + comments + '\'' +
                ", ratings=" + ratings +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasketItem that = (BasketItem) o;

        if (Float.compare(that.ratings, ratings) != 0) return false;
        if (!stockItemId.equals(that.stockItemId)) return false;
        if (!title.equals(that.title)) return false;
        if (!manufacturer.equals(that.manufacturer)) return false;
        if (!price.equals(that.price)) return false;
        if (!quantity.equals(that.quantity)) return false;
        return comments.equals(that.comments);
    }

    @Override
    public int hashCode() {
        int result = stockItemId.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + manufacturer.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + quantity.hashCode();
        result = 31 * result + comments.hashCode();
        result = 31 * result + (ratings != +0.0f ? Float.floatToIntBits(ratings) : 0);
        return result;
    }

    public void setRating(float rating) {
    }
}
