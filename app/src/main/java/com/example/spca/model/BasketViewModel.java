package com.example.spca.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

// Create a ViewModel class to hold the data and logic for the BasketActivity
public class BasketViewModel extends ViewModel {
    private DatabaseReference basketReference;
    private MutableLiveData<List<BasketItem>> basketItemsLiveData;

    public BasketViewModel() {
        basketReference = FirebaseDatabase.getInstance().getReference().child("Basket").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        basketItemsLiveData = new MutableLiveData<>();
        loadBasketItems();
    }

    public LiveData<List<BasketItem>> getBasketItemsLiveData() {
        return basketItemsLiveData;
    }

    private void loadBasketItems() {
        basketReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<BasketItem> basketItems = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    BasketItem item = snapshot.getValue(BasketItem.class);
                    basketItems.add(item);
                }
                basketItemsLiveData.setValue(basketItems);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }
}
