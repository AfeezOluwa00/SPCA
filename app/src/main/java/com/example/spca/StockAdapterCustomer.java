package com.example.spca;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
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

        // Check if imageUrl is not null before loading with Glide
        if (stockItem.getImageUrl() != null) {
            Glide.with(holder.itemView.getContext())
                    .load(stockItem.getImageUrl())
                    .into(holder.imageViewProduct);
        } else {
            // Handle the case where imageUrl is null, for example, set a placeholder image
            holder.imageViewProduct.setImageResource(R.drawable.ic_launcher_background);
        }
    }


    @Override
    public int getItemCount() {
        return stockList.size();
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
        //    imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
        }
    }
}
