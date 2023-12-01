package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyMedicineBookActivity extends AppCompatActivity {

    EditText edname, edaddress, edcontact, edpincode;
    Button btnBMCart, btnBMBBook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        edname = findViewById(R.id.editTextBMBFullName);
        edpincode = findViewById(R.id.editTextBMBPinCode);
        edaddress = findViewById(R.id.editTextBMBAddress);
        edcontact = findViewById(R.id.editTextBMBContactNumber);
        btnBMCart = findViewById(R.id.buttonBMBBack);
        btnBMBBook = findViewById(R.id.buttonBMBBook);


        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

        btnBMBBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                    String username = sharedPreferences.getString("username", "");

                    Database db = new Database(getApplicationContext(), "healthcare", null, 1);

                    // Get other intent data
                    Intent intent = getIntent();
                    String date = intent.getStringExtra("date");
                    String time = intent.getStringExtra("time");
                    float price = intent.getFloatExtra("price", 0.0f);

                    // Add the order
                    db.addOrder(username, edname.getText().toString(), edaddress.getText().toString(),
                            edcontact.getText().toString(), Integer.parseInt(edpincode.getText().toString()),
                            date, time, price, "medicine");

                    // Remove from cart
                    db.removeCart(username, "medicine");

                    Toast.makeText(getApplicationContext(), "Your Booking done successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(BuyMedicineBookActivity.this, HomeActivity.class));
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "An error occurred while processing your request", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnBMCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineBookActivity.this, CartBuyMedicineActivity.class));
            }
        });
    }
}