package com.example.spca.customer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import com.example.spca.R;
import com.example.spca.customer.ThankYouActivity;
import com.example.spca.model.AdditionalDetails;
import com.example.spca.model.BasketItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class AdditionalDetailsActivity extends AppCompatActivity {
    private EditText shippingAddressEditText, cardNameEditText, cardNumberEditText, cvvEditText, expiryDateEditText;
    private RatingBar ratingBar;
    private EditText commentsEditText;
    private Button saveButton;
    private DatabaseReference additionalDetailsReference;
    private FirebaseUser currentUser;
    private ArrayList<BasketItem> purchasedItems;
    private double totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_details);

        // Initialize Firebase components
        additionalDetailsReference = FirebaseDatabase.getInstance().getReference("AdditionalDetails");
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        // Initialize EditText fields
        shippingAddressEditText = findViewById(R.id.shippingAddressEditText);
        cardNameEditText = findViewById(R.id.cardNameEditText);
        cardNumberEditText = findViewById(R.id.cardNumberEditText);
        cvvEditText = findViewById(R.id.cvvEditText);
        expiryDateEditText = findViewById(R.id.expiryDateEditText);
        ratingBar = findViewById(R.id.ratingBar);
        commentsEditText = findViewById(R.id.commentsEditText);

        // Initialize Save button
        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAdditionalDetails();
            }
        });

        // Retrieve purchased items from intent
        Intent intent = getIntent();
        totalPrice = intent.getDoubleExtra("totalPrice", 0.0);
        purchasedItems = (ArrayList<BasketItem>) intent.getSerializableExtra("purchasedItems");

        // Ensure purchasedItems is not null
        if (purchasedItems == null) {
            purchasedItems = new ArrayList<>(); // Initialize an empty list if null
        }
    }

    private void saveAdditionalDetails() {
        String shippingAddress = shippingAddressEditText.getText().toString().trim();
        String cardName = cardNameEditText.getText().toString().trim();
        String cardNumber = cardNumberEditText.getText().toString().trim();
        String cvv = cvvEditText.getText().toString().trim();
        String expiryDate = expiryDateEditText.getText().toString().trim();
        float rating = ratingBar.getRating();
        String comments = commentsEditText.getText().toString();

        if (TextUtils.isEmpty(shippingAddress) || TextUtils.isEmpty(cardName) || TextUtils.isEmpty(cardNumber) || TextUtils.isEmpty(cvv) || TextUtils.isEmpty(expiryDate)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save additional details to the database
        String userId = currentUser.getUid();
        AdditionalDetails details = new AdditionalDetails(userId, shippingAddress, cardName, cardNumber, cvv, expiryDate);
        DatabaseReference userDetailsRef = FirebaseDatabase.getInstance().getReference("AdditionalDetails").child(userId);
        userDetailsRef.setValue(details);

        // Update the BasketItem with rating and comments
        for (BasketItem item : purchasedItems) {
            item.setRating(rating);
            item.setComments(comments);
        }

        // Save purchased items to the database as purchase history
        savePurchaseHistory();

        // Remove items from the user's basket
        DatabaseReference basketReference = FirebaseDatabase.getInstance().getReference().child("Basket").child(userId);
        basketReference.removeValue();

        // Proceed to ThankYouActivity
        startActivity(new Intent(this, ThankYouActivity.class));
        finish(); // Finish the current activity
    }

    private void savePurchaseHistory() {
        // Save purchased items to the database as purchase history
        String userId = currentUser.getUid();
        DatabaseReference purchaseHistoryRef = FirebaseDatabase.getInstance().getReference("PurchaseHistory").child(userId);

        // Get current timestamp
        long timestamp = System.currentTimeMillis();

        // Add each purchased item to purchase history with timestamp
        for (BasketItem item : purchasedItems) {
            String key = purchaseHistoryRef.push().getKey();
            if (key != null) {
                // Include timestamp along with the item
                purchaseHistoryRef.child(key).setValue(item);
            }
        }
    }
}
