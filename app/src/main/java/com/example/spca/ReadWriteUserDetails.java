package com.example.spca;

// ReadWriteUserDetails.java
public class ReadWriteUserDetails {
    public String email, password, mobile, name,role;  // User's default city

    // Default constructor required for Firebase
    public ReadWriteUserDetails() {
    }



    public ReadWriteUserDetails(String textEmail, String textPassword, String textMobile, String textName,String role) {
        this.email = textEmail;
        this.password = textPassword;
        this.mobile = textMobile;
        this.name = textName;
        this.role = role;
    }
}
