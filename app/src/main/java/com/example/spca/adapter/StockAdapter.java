package com.example.spca.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.spca.R;
import com.example.spca.model.StockItem;

import java.util.List;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.StockViewHolder> {

    private List<StockItem> stockList;

    public StockAdapter(List<StockItem> stockList) {
        this.stockList = stockList;
    }

    @NonNull
    @Override
    public StockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stock, parent, false);
        return new StockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StockViewHolder holder, int position) {
        StockItem stockItem = stockList.get(position);
        holder.textViewTitle.setText(stockItem.getTitle());
        holder.textViewManufacturer.setText(stockItem.getManufacturer());
        holder.textViewPrice.setText(String.valueOf(stockItem.getPrice()));
        holder.textViewQuantity.setText(String.valueOf(stockItem.getQuantity()));
        holder.textViewCategory.setText(stockItem.getCategory());

        // Load image using Glide
        Glide.with(holder.itemView)
                .load(stockItem.getImageUrl())
                .placeholder(R.drawable.ic_launcher_background) // Placeholder image while loading
                .error(R.drawable.ic_launcher_foreground) // Image to show if loading fails
                .into(holder.imageViewProduct);
    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }

    public void setOnItemClickListener(StockAdapter.OnItemClickListener stockItem) {
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class StockViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewManufacturer, textViewPrice, textViewQuantity, textViewCategory;
        ImageView imageViewProduct;

        public StockViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewManufacturer = itemView.findViewById(R.id.textViewManufacturer);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
            textViewCategory = itemView.findViewById(R.id.textViewCategory);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
        }
    }
}
