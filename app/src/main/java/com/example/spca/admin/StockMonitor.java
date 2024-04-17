package com.example.spca.admin;

import com.example.spca.model.StockItem;

public class StockMonitor implements StockItemObserver {

    @Override
    public void onStockItemAdded(StockItem item) {

    }

    @Override
    public void onStockItemUpdated(StockItem item) {
        System.out.println("Stock item updated: " + item.getTitle() + ", Price: " + item.getPrice());
        // Perform additional actions as needed
    }
}
