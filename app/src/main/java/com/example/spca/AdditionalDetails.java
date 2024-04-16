package com.example.spca;

public class AdditionalDetails {
    private String userId;
    private String shippingAddress;
    private String cardName;
    private String cardNumber;
    private String cvv;
    private String expiryDate;

    public AdditionalDetails() {
        // Default constructor required for Firebase
    }

    public AdditionalDetails(String userId, String shippingAddress, String cardName, String cardNumber, String cvv, String expiryDate) {
        this.userId = userId;
        this.shippingAddress = shippingAddress;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    // Getter and setter methods
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
