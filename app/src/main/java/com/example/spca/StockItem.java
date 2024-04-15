package com.example.spca;

public class StockItem {
    private String itemId;
    private String title;
    private String manufacturer;
    private String price;
    private String quantity;
    private String category;
    private String imageUrl;

    // Constructors, getters, and setters
    public StockItem() {
        // Default constructor required for Firebase
    }

    public StockItem(String itemId, String title, String manufacturer, String price, String quantity, String category, String imageUrl) {
        this.itemId = itemId;
        this.title = title;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
