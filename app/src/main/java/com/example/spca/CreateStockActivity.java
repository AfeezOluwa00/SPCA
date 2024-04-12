package com.example.spca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

// AdminActivity.java

public class CreateStockActivity extends AppCompatActivity {
    private EditText titleEditText, manufacturerEditText, priceEditText, categoryEditText;
    private Button addButton;
    private ImageView imageView;
    private Uri imageUri;
    private DatabaseReference stockReference;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_stock);

        // Initialize Firebase components
        stockReference = FirebaseDatabase.getInstance().getReference("Stock");
        storageReference = FirebaseStorage.getInstance().getReference("Images");

        // Initialize EditText fields
        titleEditText = findViewById(R.id.titleEditText);
        manufacturerEditText = findViewById(R.id.manufacturerEditText);
        priceEditText = findViewById(R.id.priceEditText);
        categoryEditText = findViewById(R.id.categoryEditText);

        // Initialize ImageView for image selection
        imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        // Initialize Add button
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStockItem();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

    private void addStockItem() {
        String title = titleEditText.getText().toString().trim();
        String manufacturer = manufacturerEditText.getText().toString().trim();
        String price = priceEditText.getText().toString().trim();
        String category = categoryEditText.getText().toString().trim();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(manufacturer) || TextUtils.isEmpty(price) || TextUtils.isEmpty(category) || imageUri == null) {
            Toast.makeText(this, "Please fill in all fields and select an image", Toast.LENGTH_SHORT).show();
            return;
        }

        // Upload image to Firebase Storage
        final StorageReference imageReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
        imageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String imageUrl = uri.toString();
                        // Create stock item object
                        StockItem stockItem = new StockItem(title, manufacturer, price, category, imageUrl);

                        // Save stock item to Firebase Database
                        String itemId = stockReference.push().getKey();
                        stockReference.child(itemId).setValue(stockItem);

                        Toast.makeText(CreateStockActivity.this, "Stock item added successfully", Toast.LENGTH_SHORT).show();

                        // Clear input fields
                        titleEditText.setText("");
                        manufacturerEditText.setText("");
                        priceEditText.setText("");
                        categoryEditText.setText("");
                        imageView.setImageResource(R.drawable.ic_launcher_background);
                        imageUri = null;
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CreateStockActivity.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
}
