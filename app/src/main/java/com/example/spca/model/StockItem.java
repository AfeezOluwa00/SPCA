package com.example.spca.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;


public class StockItem implements Parcelable {
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

    protected StockItem(Parcel in) {
        itemId = in.readString();
        title = in.readString();
        manufacturer = in.readString();
        price = in.readString();
        quantity = in.readString();
        category = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<StockItem> CREATOR = new Creator<StockItem>() {
        @Override
        public StockItem createFromParcel(Parcel in) {
            return new StockItem(in);
        }

        @Override
        public StockItem[] newArray(int size) {
            return new StockItem[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(itemId);
        dest.writeString(title);
        dest.writeString(manufacturer);
        dest.writeString(price);
        dest.writeString(quantity);
        dest.writeString(category);
        dest.writeString(imageUrl);
    }
}
