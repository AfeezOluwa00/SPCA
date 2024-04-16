package com.example.spca.customer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spca.R;
import com.example.spca.adapter.BasketAdapter;
import com.example.spca.model.BasketItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BasketActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BasketAdapter adapter;
    private DatabaseReference basketReference;
    private String currentUserUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_basket);

        recyclerView = findViewById(R.id.recyclerViewBasket);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BasketAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        basketReference = FirebaseDatabase.getInstance().getReference().child("Basket").child(currentUserUid);

        // Listen for changes in the basket contents
        basketReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<BasketItem> basketItems = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Parse the snapshot to BasketItem objects
                    BasketItem item = snapshot.getValue(BasketItem.class);
                    basketItems.add(item);
                }
                adapter.updateData(basketItems);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }
}
