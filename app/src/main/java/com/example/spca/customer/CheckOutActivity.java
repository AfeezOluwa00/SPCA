package com.example.spca.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spca.R;
import com.example.spca.model.BasketItem;

import java.util.ArrayList;

public class CheckOutActivity extends AppCompatActivity {

    private TextView totalPriceTextView;
    private ArrayList<BasketItem> purchasedItems;
    private double totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        totalPriceTextView = findViewById(R.id.totalPriceTextView);

        // Retrieve total price and purchased items from intent
        Intent intent = getIntent();
        totalPrice = intent.getDoubleExtra("totalPrice", 0.0);
        purchasedItems = (ArrayList<BasketItem>) intent.getSerializableExtra("purchasedItems");

        // Display total price
        totalPriceTextView.setText(String.format("Total Price: $%.2f", totalPrice));

        Button proceedToPaymentButton = findViewById(R.id.proceedToPaymentButton);
        proceedToPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceedToPayment();
            }
        });
    }

    private void proceedToPayment() {
        // Start activity for additional details
        Intent intent = new Intent(this, AdditionalDetailsActivity.class);
        intent.putExtra("totalPrice", totalPrice);
        intent.putExtra("purchasedItems", purchasedItems);
        startActivity(intent);
    }
}
