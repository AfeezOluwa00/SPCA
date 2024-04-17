package com.example.spca.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.spca.R;
import com.example.spca.adapter.BasketAdapter;
import com.example.spca.model.BasketItem;
import com.example.spca.model.BasketViewModel;

import java.util.ArrayList;
import java.util.List;

public class BasketActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BasketAdapter adapter;
    private BasketViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_basket);

        recyclerView = findViewById(R.id.recyclerViewBasket);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BasketAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(BasketViewModel.class);
        viewModel.getBasketItemsLiveData().observe(this, new Observer<List<BasketItem>>() {
            @Override
            public void onChanged(List<BasketItem> basketItems) {
                adapter.updateData(basketItems);
            }
        });
    }
}
