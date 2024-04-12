package com.example.spca;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class ViewStockActivity extends AppCompatActivity {

    private RecyclerView recyclerViewStock;
    private StockAdapter stockAdapter;
    private List<StockItem> stockItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stock);

        recyclerViewStock = findViewById(R.id.recyclerViewStock);
        recyclerViewStock.setLayoutManager(new LinearLayoutManager(this));
        stockItemList = new ArrayList<>();
        stockAdapter = new StockAdapter(stockItemList);
        recyclerViewStock.setAdapter(stockAdapter);

        fetchStockDataFromFirebase();
    }

    private void fetchStockDataFromFirebase() {
        FirebaseDatabase.getInstance().getReference("Stock")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        stockItemList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            StockItem stockItem = snapshot.getValue(StockItem.class);
                            stockItemList.add(stockItem);
                        }
                        stockAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(ViewStockActivity.this, "Failed to fetch stock data", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
