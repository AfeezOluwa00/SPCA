package com.example.spca.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.spca.R;
import com.example.spca.model.StockItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddToBasketActivity extends AppCompatActivity implements BasketSubject {

    private StockItem selectedStockItem;
    private EditText editTextQuantity;
    private DatabaseReference basketReference;
    private List<BasketObserver> observers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_basket);

        // Get the selected StockItem from the intent
        selectedStockItem = getIntent().getParcelableExtra("selectedStockItem");

        // Initialize Firebase Database reference for the user's basket
        String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        basketReference = FirebaseDatabase.getInstance().getReference().child("Basket").child(currentUserUid);

        // Display the details of the selected StockItem
        displayStockItemDetails();

        // Initialize EditText for quantity input
        editTextQuantity = findViewById(R.id.editTextQuantity);

        Button addToBasketButton = findViewById(R.id.buttonAddToBasket);
        addToBasketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToBasket(v); // Call the addToBasket method when the button is clicked
            }
        });
    }

    private void displayStockItemDetails() {
        // Display the details of the selected StockItem in your layout
        // For example:
        TextView textViewTitle = findViewById(R.id.textViewTitle);
        textViewTitle.setText(selectedStockItem.getTitle());

        TextView textViewManufacturer = findViewById(R.id.textViewManufacturer);
        textViewManufacturer.setText(selectedStockItem.getManufacturer());

        TextView textViewPrice = findViewById(R.id.textViewPrice);
        textViewPrice.setText(String.valueOf(selectedStockItem.getPrice()));

        // Add any other TextViews to display other details

        // Load image using Glide or any other image loading library
        ImageView imageViewProduct = findViewById(R.id.imageViewProduct);
        Glide.with(this).load(selectedStockItem.getImageUrl()).into(imageViewProduct);
    }

    public void addToBasket(View view) {
        String quantityStr = editTextQuantity.getText().toString().trim();
        if (!quantityStr.isEmpty()) {
            int quantity = Integer.parseInt(quantityStr);
            int quantityInStock = Integer.parseInt(selectedStockItem.getQuantity());
            if (quantity > 0 && quantity <= quantityInStock) {
                selectedStockItem.setQuantity(quantityStr); // Set the quantity in the selectedStockItem
                basketReference.push().setValue(selectedStockItem);
                Toast.makeText(this, "Item added to basket", Toast.LENGTH_SHORT).show();
                notifyObservers(selectedStockItem); // Notify observers of the change
                finish();
            } else {
                Toast.makeText(this, "Invalid quantity", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter quantity", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void registerObserver(BasketObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(BasketObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(StockItem item) {
        for (BasketObserver observer : observers) {
            observer.onItemAddedToBasket(item);
        }
    }
}
