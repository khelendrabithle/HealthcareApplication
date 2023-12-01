package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import android.database.SQLException;

public class AppointmentActivity extends AppCompatActivity {
    EditText ed1, ed2, ed3, ed4;
    TextView tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton, timeButton, btnBook, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        tv = findViewById(R.id.textViewAppTitle);
        ed1 = findViewById(R.id.editTextAppointUsername);
        ed2 = findViewById(R.id.editTextAppointmentAddress);
        ed3 = findViewById(R.id.editTextContact);
        ed4 = findViewById(R.id.editTextFees);
        dateButton = findViewById(R.id.buttonAppDate);
        timeButton = findViewById(R.id.buttonAppTime);
        btnBook = findViewById(R.id.buttonBookAppointment);
        btnBack = findViewById(R.id.buttonBackAppointment);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contactno = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contactno);
        ed4.setText("Cons fees" + fees + "/-");

        initDatePicker();
        dateButton.setOnClickListener(view -> datePickerDialog.show());

        initTimePicker();
        timeButton.setOnClickListener(view -> timePickerDialog.show());

        btnBack.setOnClickListener(view -> startActivity(new Intent(AppointmentActivity.this, FindDoctorActivity.class)));

        btnBook.setOnClickListener(view -> {
            if (dateButton.getText().toString().equals("Select Date") || timeButton.getText().toString().equals("Select Time")) {
                Toast.makeText(getApplicationContext(), "Please select date and time", Toast.LENGTH_LONG).show();
                return;
            }

            SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
            String username = sharedPreferences.getString("username", "");

            if (username.isEmpty()) {
                Log.e("AppointmentActivity", "Username not found in SharedPreferences");
                Toast.makeText(getApplicationContext(), "Username not found", Toast.LENGTH_LONG).show();
                return;
            }

            Database db = new Database(getApplicationContext(), "healthcare", null, 1);
            try {
                // No need to explicitly open the database

                if (db.checkAppointmentExists(username, title + "=>" + fullname, address, contactno, dateButton.getText().toString(), timeButton.getText().toString()) == 1) {
                    Toast.makeText(getApplicationContext(), "Appointment already Booked!", Toast.LENGTH_LONG).show();
                } else {
                    db.addOrder(username, title + "=>" + fullname, address, contactno, 0, dateButton.getText().toString(), timeButton.getText().toString(), Float.parseFloat(fees), "appointment");
                    Log.d("AppointmentActivity", "Appointment booked successfully");
                    Toast.makeText(getApplicationContext(), "Your appointment is done Successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AppointmentActivity.this, HomeActivity.class));
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Log the full stack trace
                Log.e("AppointmentActivity", "SQL Error: " + e.getMessage());
                Toast.makeText(getApplicationContext(), "Error booking appointment. SQL Error. Check logs for details.", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace(); // Log the full stack trace
                Log.e("AppointmentActivity", "Error booking appointment: " + e.getMessage());
                Toast.makeText(getApplicationContext(), "Error booking appointment. Check logs for details.", Toast.LENGTH_LONG).show();
            } finally {
                // No need to explicitly close the database
            }
        });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, i, i1, i2) -> {
            i1 = i1 + 1;
            dateButton.setText(i2 + "/" + i1 + "/" + i);
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000);
    }

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = (timePicker, i, i1) -> timeButton.setText(i + ":" + i1);

        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int mins = cal.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this, style, timeSetListener, hrs, mins, true);
    }
}
