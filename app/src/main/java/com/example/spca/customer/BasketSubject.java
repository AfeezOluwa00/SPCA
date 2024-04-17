package com.example.spca.customer;

import com.example.spca.model.StockItem;

public interface BasketSubject {
    void registerObserver(BasketObserver observer);
    void removeObserver(BasketObserver observer);
    void notifyObservers(StockItem item);
}
