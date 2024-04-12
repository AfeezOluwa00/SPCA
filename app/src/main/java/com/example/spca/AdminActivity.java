package com.example.spca;

=import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Button for creating stock
        Button createStockButton = findViewById(R.id.createStockButton);
        createStockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open CreateStockActivity
                Intent intent = new Intent(AdminActivity.this, CreateStockActivity.class);
                startActivity(intent);
            }
        });

        // Button for viewing stock
        Button viewStockButton = findViewById(R.id.viewStockButton);
        viewStockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open ViewStockActivity
                Intent intent = new Intent(AdminActivity.this, ViewStockActivity.class);
                startActivity(intent);
            }
        });

        // Button for searching
        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open SearchActivity
                Intent intent = new Intent(AdminActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        // Button for updating stock
        Button updateStockButton = findViewById(R.id.updateStockButton);
        updateStockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open UpdateStockActivity
                Intent intent = new Intent(AdminActivity.this, UpdateStockActivity.class);
                startActivity(intent);
            }
        });
    }
}
