package com.example.spca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.spca.admin.AdminActivity;
import com.example.spca.customer.UserActivity;
import com.example.spca.model.ReadWriteUserDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private EditText editTextLoginEmail, editTextLoginPwd;
    private ProgressBar progressBar;
    private FirebaseAuth authProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextLoginEmail = findViewById(R.id.email);
        editTextLoginPwd = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        authProfile = FirebaseAuth.getInstance();

        Button buttonLogin = findViewById(R.id.LoginButton);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String textEmail = editTextLoginEmail.getText().toString();
        String textPwd = editTextLoginPwd.getText().toString();

        if (TextUtils.isEmpty(textEmail) || !Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()) {
            editTextLoginEmail.setError("Enter a valid email address");
            editTextLoginEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(textPwd)) {
            editTextLoginPwd.setError("Password is required");
            editTextLoginPwd.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        authProfile.signInWithEmailAndPassword(textEmail, textPwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);

                        if (task.isSuccessful()) {
                            // Check user role and redirect accordingly
                            fetchUserRoleAndRedirect(textEmail);
                        } else {
                            Toast.makeText(Login.this, "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void fetchUserRoleAndRedirect(String email) {
        FirebaseDatabase.getInstance().getReference("Registered Users")
                .orderByChild("email")
                .equalTo(email)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            ReadWriteUserDetails userDetails = snapshot.getValue(ReadWriteUserDetails.class);
                            if (userDetails != null) {
                                String role = userDetails.getRole();
                                if (role.equals("Administrator")) {
                                    startActivity(new Intent(Login.this, AdminActivity.class));
                                } else {
                                    startActivity(new Intent(Login.this, UserActivity.class));
                                }
                                finish(); // Finish the current activity
                                return;
                            }
                        }
                        // No user found with the given email
                        Toast.makeText(Login.this, "User not found", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(Login.this, "Failed to fetch user data", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
