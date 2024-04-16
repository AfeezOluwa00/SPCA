package com.example.spca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button EnterAdditionalDetails = findViewById(R.id.additionalDetails);
        EnterAdditionalDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open CreateStockActivity
                Intent intent = new Intent(UserActivity.this, AdditionalDetailsActivity.class);
                startActivity(intent);
            }
        });

        Button SearchItems = findViewById(R.id.searchItems);
        SearchItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open CreateStockActivity
                Intent intent = new Intent(UserActivity.this, SearchActivityCustomer.class);
                startActivity(intent);
            }
        });
    }
}