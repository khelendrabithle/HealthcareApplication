package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OTPVerificationActivity extends AppCompatActivity {
    private EditText otpEditText;
    Button Verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);

        otpEditText = findViewById(R.id.editTextOtp);
        Verify = findViewById(R.id.buttonVerify);

        Verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredOtp = otpEditText.getText().toString().trim();

            }
        });



    }
}