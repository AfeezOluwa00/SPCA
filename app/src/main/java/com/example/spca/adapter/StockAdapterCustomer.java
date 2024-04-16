package com.example.spca.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.spca.R;
import com.example.spca.customer.AddToBasketActivity;
import com.example.spca.model.StockItem;

import java.util.List;

public class StockAdapterCustomer extends RecyclerView.Adapter<StockAdapterCustomer.StockViewHolder> {

    private List<StockItem> stockList;

    public StockAdapterCustomer(List<StockItem> stockList) {
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
        Glide.with(holder.itemView.getContext()).load(stockItem.getImageUrl()).into(holder.imageViewProduct);

        holder.addToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start AddToBasketActivity and pass the selected StockItem
                Intent intent = new Intent(v.getContext(), AddToBasketActivity.class);
                intent.putExtra("selectedStockItem", stockItem);
                v.getContext().startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return stockList.size();
    }

    public void setStockList(List<StockItem> stockList) {
        this.stockList = stockList;
        notifyDataSetChanged();
    }

    public class StockViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewManufacturer, textViewPrice, textViewQuantity, textViewCategory;
        ImageView imageViewProduct;

        Button addToBasket;

        public StockViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewManufacturer = itemView.findViewById(R.id.textViewManufacturer);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
            textViewCategory = itemView.findViewById(R.id.textViewCategory);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            addToBasket  = itemView.findViewById(R.id.buttonAddToBasket);
        }
    }
}
