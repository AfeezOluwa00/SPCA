// StockItemFactory.java
package com.example.spca.admin;

import com.example.spca.model.StockItem;

public interface StockItemFactory {
    StockItem createStockItem(String title, String manufacturer, String price, String quantity, String category, String imageUrl);
}
