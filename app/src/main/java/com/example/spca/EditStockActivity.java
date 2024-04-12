package com.example.spca;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditStockActivity extends AppCompatActivity {
    private EditText titleEditText, manufacturerEditText, priceEditText, categoryEditText, quantityEditText;
    private Button updateButton;
    private DatabaseReference stockReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_stock);

        // Initialize Firebase components
        stockReference = FirebaseDatabase.getInstance().getReference("Stock");

        // Initialize EditText fields
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

        // Retrieve stock item details passed from previous activity
        StockItem stockItem = (StockItem) getIntent().getSerializableExtra("stockItem");

        // Display stock item details in EditText fields
        titleEditText.setText(stockItem.getTitle());
        manufacturerEditText.setText(stockItem.getManufacturer());
        priceEditText.setText(String.valueOf(stockItem.getPrice()));
        categoryEditText.setText(stockItem.getCategory());
        quantityEditText.setText(String.valueOf(stockItem.getQuantity()));
    }

    private void updateStockItem() {
        String title = titleEditText.getText().toString().trim();
        String manufacturer = manufacturerEditText.getText().toString().trim();
        String price = priceEditText.getText().toString().trim();
        String category = categoryEditText.getText().toString().trim();
        String quantity = quantityEditText.getText().toString().trim();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(manufacturer) || TextUtils.isEmpty(price) || TextUtils.isEmpty(category) || TextUtils.isEmpty(quantity)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Retrieve the stock item ID passed from previous activity
        String itemId = getIntent().getStringExtra("itemId");

        // Update the stock item details in Firebase Database
        StockItem updatedStockItem = new StockItem(title, manufacturer,price,quantity, category, "");
        stockReference.child(itemId).setValue(updatedStockItem);

        Toast.makeText(this, "Stock item updated successfully", Toast.LENGTH_SHORT).show();

        // Finish the activity
        finish();
    }
}
