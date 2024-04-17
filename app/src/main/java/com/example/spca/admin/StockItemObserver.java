package com.example.spca.admin;

import com.example.spca.model.StockItem;

public interface StockItemObserver {
    void onStockItemAdded(StockItem item);
    void onStockItemUpdated(StockItem item);
}
