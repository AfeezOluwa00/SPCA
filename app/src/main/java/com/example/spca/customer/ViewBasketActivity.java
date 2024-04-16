package com.example.spca.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spca.adapter.BasketAdapter;
import com.example.spca.model.BasketItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;
import java.util.ArrayList;
import java.util.List;
import com.example.spca.R;

public class ViewBasketActivity extends AppCompatActivity {

    private RecyclerView recyclerViewBasket;
    private BasketAdapter adapter;
    private List<BasketItem> basketItemList;
    private DatabaseReference basketReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_basket);

        Button clearBasketButton = findViewById(R.id.buttonClearBasket);
        clearBasketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBasket();
            }
        });

        Button checkoutButton = findViewById(R.id.buttonProceedToCheckout);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceedToCheckout();
            }
        });

        // Initialize Firebase Database
        String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        basketReference = FirebaseDatabase.getInstance().getReference().child("Basket").child(currentUserUid);

        // Initialize RecyclerView
        recyclerViewBasket = findViewById(R.id.recyclerViewBasket);
        recyclerViewBasket.setHasFixedSize(true);
        recyclerViewBasket.setLayoutManager(new LinearLayoutManager(this));

        // Initialize basketItemList
        basketItemList = new ArrayList<>();

        // Set up RecyclerView adapter
        adapter = new BasketAdapter(basketItemList);
        recyclerViewBasket.setAdapter(adapter);

        // Retrieve basket items from Firebase
        retrieveBasket();
    }

    private void clearBasket() {
        basketReference.removeValue(); // Remove all items from Firebase
        Toast.makeText(this, "Basket cleared", Toast.LENGTH_SHORT).show();
    }

    private void proceedToCheckout() {
        // Start a new activity for checkout
        Intent intent = new Intent(this, CheckOutActivity.class);
        startActivity(intent);
    }

    private void retrieveBasket() {
        basketReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                basketItemList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    BasketItem basketItem = snapshot.getValue(BasketItem.class);
                    basketItemList.add(basketItem);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });
    }
}
