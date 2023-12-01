package com.example.healthcareapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

// ForgotPasswordActivity.java
public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText emailEditText;
    TextView tv;
    Button sendOtpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailEditText = findViewById(R.id.editTextEmail);
        sendOtpButton = findViewById(R.id.buttonSendOTP);

        sendOtpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();

                // TODO: Implement logic to send OTP to the provided email address

                // Assuming the OTP sending is successful
                Intent intent = new Intent(ForgotPasswordActivity.this, OTPVerificationActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
            }
        });
    }

    private int generateOTP() {
        Random random = new Random();
        return 100000 + random.nextInt(900000); // Generate a 6-digit OTP
    }
}
