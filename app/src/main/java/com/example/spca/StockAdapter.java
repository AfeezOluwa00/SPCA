package com.example.spca;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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
        // Bind other data if needed
    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }

    public class StockViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewManufacturer, textViewPrice;

        public StockViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewManufacturer = itemView.findViewById(R.id.textViewManufacturer);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            // Initialize other views if needed
        }
    }
}
