package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText edNewPassword, edNewConfirmPassword;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        edNewPassword = findViewById(R.id.editTextNewPassword);
        edNewConfirmPassword = findViewById(R.id.editTextNewConfirmPassword);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPassword = edNewPassword.getText().toString();
                String newConfirmPassword = edNewConfirmPassword.getText().toString();
                if (newPassword.length() == 0 || newConfirmPassword.length() == 0){
                    Toast.makeText(getApplicationContext(),"Create Password",Toast.LENGTH_SHORT).show();
                }
            }
        });
        startActivity(new Intent(ResetPasswordActivity.this,LoginActivity.class));
    }
}