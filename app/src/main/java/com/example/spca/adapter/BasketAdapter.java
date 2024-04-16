package com.example.spca.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spca.model.BasketItem;
import com.example.spca.R;

import java.util.List;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.BasketViewHolder> {

    private List<BasketItem> basketItemList;

    public BasketAdapter(List<BasketItem> basketItemList) {
        this.basketItemList = basketItemList;
    }

    @NonNull
    @Override
    public BasketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_basket, parent, false);
        return new BasketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BasketViewHolder holder, int position) {
        BasketItem basketItem = basketItemList.get(position);
        holder.textViewTitle.setText(basketItem.getTitle());
        holder.textViewManufacturer.setText(basketItem.getManufacturer());
        holder.textViewPrice.setText(String.valueOf(basketItem.getPrice()));
        holder.textViewQuantity.setText(String.valueOf(basketItem.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return basketItemList.size();
    }

    public void updateData(List<BasketItem> basketItems) {
        this.basketItemList.clear();
        this.basketItemList.addAll(basketItems);
        notifyDataSetChanged();
    }

    public static class BasketViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewManufacturer, textViewPrice, textViewQuantity;

        public BasketViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewManufacturer = itemView.findViewById(R.id.textViewManufacturer);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
        }
    }
}
