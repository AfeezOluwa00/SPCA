package com.example.spca;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.annotation.NonNull;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
                            Toast.makeText(Login.this, "User is logged in now", Toast.LENGTH_SHORT).show();

                            // Move to the HomePage activity after successful login
                            Intent intent = new Intent(Login.this, UserActivity.class);
                            intent.putExtra("keyname", textEmail);
                            startActivity(intent);

                        } else {
                            Toast.makeText(Login.this, "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
