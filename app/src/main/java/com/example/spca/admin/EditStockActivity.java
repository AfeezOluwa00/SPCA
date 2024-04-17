package com.example.spca.admin;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spca.R;
import com.example.spca.model.StockItem;
import com.example.spca.admin.StockItemObserver;
public class EditStockActivity extends AppCompatActivity implements StockItemObserver {

    private EditText titleEditText, manufacturerEditText, priceEditText, categoryEditText, quantityEditText;
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_stock);

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

        // Notify observers about the stock item update
        StockItem updatedStockItem = new StockItem(itemId, title, manufacturer, price, quantity, category, "");
        onStockItemUpdated(updatedStockItem);

        // Finish the activity
        finish();
    }

    @Override
    public void onStockItemAdded(StockItem item) {

    }

    @Override
    public void onStockItemUpdated(StockItem item) {
        // Handle the event when a stock item is updated
        // Here you can perform any action needed, such as updating UI or performing additional operations
    }
}
