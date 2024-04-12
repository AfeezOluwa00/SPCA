package com.example.spca;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    private EditText editTextRegisterEmail, editTextRegisterMobile, editTextRegisterPwd, editTextRegisterConfirmPwd, editTextRegisterName;
    private ProgressBar progressBar;
    private RadioGroup roleRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressBar = findViewById(R.id.progressBar);
        editTextRegisterEmail = findViewById(R.id.email);
        editTextRegisterPwd = findViewById(R.id.password);
        editTextRegisterConfirmPwd = findViewById(R.id.confirmPassword);
        editTextRegisterMobile = findViewById(R.id.number);
        editTextRegisterName = findViewById(R.id.name);
        roleRadioGroup = findViewById(R.id.role_radio_group);

        Button buttonRegister = findViewById(R.id.RegisterButton);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textEmail = editTextRegisterEmail.getText().toString();
                String textPassword = editTextRegisterPwd.getText().toString();
                String textConfirmedPassword = editTextRegisterConfirmPwd.getText().toString();
                String textMobile = editTextRegisterMobile.getText().toString();
                String textName = editTextRegisterName.getText().toString();
                String role = roleRadioGroup.getCheckedRadioButtonId() == R.id.customer_radio_button ? "Customer" : "Administrator";

                // Validate fields
                if (TextUtils.isEmpty(textEmail) || TextUtils.isEmpty(textPassword) || TextUtils.isEmpty(textConfirmedPassword) ||
                        TextUtils.isEmpty(textMobile) || TextUtils.isEmpty(textName)) {
                    Toast.makeText(Register.this, "Please fill in all fields", Toast.LENGTH_LONG).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()) {
                    Toast.makeText(Register.this, "Please enter a valid email", Toast.LENGTH_LONG).show();
                    editTextRegisterEmail.setError("Valid Email is required");
                    editTextRegisterEmail.requestFocus();
                } else if (textMobile.length() != 10 || !textMobile.matches("[6-9][0-9]{9}")) {
                    Toast.makeText(Register.this, "Please enter a valid mobile number", Toast.LENGTH_LONG).show();
                    editTextRegisterMobile.setError("Valid Mobile Number is required");
                    editTextRegisterMobile.requestFocus();
                } else if (textPassword.length() < 6) {
                    Toast.makeText(Register.this, "Password should be at least 6 characters", Toast.LENGTH_LONG).show();
                    editTextRegisterPwd.setError("Password too weak");
                    editTextRegisterPwd.requestFocus();
                } else if (!textPassword.equals(textConfirmedPassword)) {
                    Toast.makeText(Register.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                    editTextRegisterPwd.setError("Password mismatch");
                    editTextRegisterPwd.requestFocus();
                    editTextRegisterPwd.clearComposingText();
                    editTextRegisterConfirmPwd.clearComposingText();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    registerUser(textEmail, textPassword, textConfirmedPassword, textMobile, textName, role);
                }
            }
        });
    }

    private void registerUser(String textEmail, String textPassword, String textConfirmedPassword, String textMobile, String textName, String role) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(textEmail, textPassword).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(textName).build();
                    firebaseUser.updateProfile(profileChangeRequest);

                    // Create the user details object
                    ReadWriteUserDetails userDetails = new ReadWriteUserDetails(textEmail, textPassword, textMobile, textName, role);

                    // Save user details to the database
                    DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered Users");
                    referenceProfile.child(firebaseUser.getUid()).setValue(userDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                firebaseUser.sendEmailVerification();
                                Toast.makeText(Register.this, "User has been created. Please verify your email", Toast.LENGTH_LONG).show();

                                // Check user role and redirect accordingly
                                Intent intent;// Finish the current activity
                                if (role.equals("Administrator")) {
                                     // Redirect to admin activity
                                    intent = new Intent(Register.this, AdminActivity.class);
                                } else {
                                    // Redirect to user activity
                                    intent = new Intent(Register.this, UserActivity.class);
                                }
                                startActivity(intent);
                                finish(); // Finish the current activity
                            } else {
                                Toast.makeText(Register.this, "User registration failed. Please try again", Toast.LENGTH_LONG).show();
                            }
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });
    }
}
