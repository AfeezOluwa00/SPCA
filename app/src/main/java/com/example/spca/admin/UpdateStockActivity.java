package com.example.spca.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spca.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateStockActivity extends AppCompatActivity {
    private EditText itemIdEditText, titleEditText, manufacturerEditText, priceEditText, categoryEditText, quantityEditText;
    private Button updateButton;
    private DatabaseReference stockReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_stock);

        // Initialize Firebase components
        stockReference = FirebaseDatabase.getInstance().getReference("Stock");

        // Initialize EditText fields
        itemIdEditText = findViewById(R.id.itemIdEditText);
        titleEditText = findViewById(R.id.titleEditText);
        manufacturerEditText = findViewById(R.id.manufacturerEditText);
        priceEditText = findViewById(R.id.priceEditText);
        categoryEditText = findViewById(R.id.categoryEditText);
        quantityEditText = findViewById(R.id.quantityEditText);

        // Initialize Update button
        updateButton = findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStockItem();
            }
        });
    }

    private void updateStockItem() {
        final String itemId = itemIdEditText.getText().toString().trim();
        final String title = titleEditText.getText().toString().trim();
        final String manufacturer = manufacturerEditText.getText().toString().trim();
        final String price = priceEditText.getText().toString().trim();
        final String category = categoryEditText.getText().toString().trim();
        final String quantity = quantityEditText.getText().toString().trim();

        if (TextUtils.isEmpty(itemId) || TextUtils.isEmpty(title) || TextUtils.isEmpty(manufacturer) || TextUtils.isEmpty(price) || TextUtils.isEmpty(category) || TextUtils.isEmpty(quantity)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Query the Firebase database for the stock item with the given item ID
        stockReference.child(itemId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Update the stock item with the modified information
                    dataSnapshot.getRef().child("title").setValue(title);
                    dataSnapshot.getRef().child("manufacturer").setValue(manufacturer);
                    dataSnapshot.getRef().child("price").setValue(price);
                    dataSnapshot.getRef().child("category").setValue(category);
                    dataSnapshot.getRef().child("quantity").setValue(quantity);

                    Toast.makeText(UpdateStockActivity.this, "Stock item updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateStockActivity.this, "Stock item with ID " + itemId + " not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UpdateStockActivity.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
