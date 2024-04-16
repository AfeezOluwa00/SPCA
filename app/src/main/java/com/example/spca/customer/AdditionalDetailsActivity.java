package com.example.spca.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spca.model.AdditionalDetails;
import com.example.spca.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdditionalDetailsActivity extends AppCompatActivity {
    private EditText shippingAddressEditText, cardNameEditText, cardNumberEditText, cvvEditText, expiryDateEditText;
    private Button saveButton;
    private DatabaseReference additionalDetailsReference;
    private FirebaseUser currentUser;

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

        // Initialize Save button
        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAdditionalDetails();
            }
        });
    }

    private void saveAdditionalDetails() {
        String shippingAddress = shippingAddressEditText.getText().toString().trim();
        String cardName = cardNameEditText.getText().toString().trim();
        String cardNumber = cardNumberEditText.getText().toString().trim();
        String cvv = cvvEditText.getText().toString().trim();
        String expiryDate = expiryDateEditText.getText().toString().trim();

        if (TextUtils.isEmpty(shippingAddress) || TextUtils.isEmpty(cardName) || TextUtils.isEmpty(cardNumber) || TextUtils.isEmpty(cvv) || TextUtils.isEmpty(expiryDate)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String userId = currentUser.getUid();
        AdditionalDetails details = new AdditionalDetails(userId, shippingAddress, cardName, cardNumber, cvv, expiryDate);
        additionalDetailsReference.child(userId).setValue(details);

        Toast.makeText(this, "Additional details saved successfully", Toast.LENGTH_SHORT).show();

        // Finish the activity
        finish();
    }
}
