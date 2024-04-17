package com.example.spca.customer;

import com.example.spca.model.StockItem;

public interface BasketObserver {
    void onItemAddedToBasket(StockItem item);
}
