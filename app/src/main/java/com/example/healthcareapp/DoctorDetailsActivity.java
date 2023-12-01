package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctor_details1 = {
            {"Doctor Name : Darshan Kakpure", "Hospital Address : Swargate", "Exp : 8yrs", "Mobile No : 9657619007", " : 600"},
            {"Doctor Name : Rutvik Tonde", "Hospital Address : Kharadi", "Exp : 10yrs", "Mobile No : 9214517544", " : 800"},
            {"Doctor Name : Niranjan Patil", "Hospital Address : Kothrud", "Exp : 4yrs", "Mobile No : 9145788954", " : 500"},
            {"Doctor Name : Sushant Khandekar", "Hospital Address : Katraj", "Exp : 7yrs", "Mobile No : 8874859624", " : 500"},
            {"Doctor Name : Pranav Solanki", "Hospital Address : Warje", "Exp : 4yrs", "Mobile No : 7795788114", " : 500"},

    };

    private String[][] doctor_details2 = {
            {"Doctor Name : Sandeep Sonwane", "Hospital Address : Rambaug", "Exp : 4yrs", "Mobile No : 9587412541", " : 500"},
            {"Doctor Name : Kuldeep Thakre", "Hospital Address : Garware", "Exp : 10yrs", "Mobile No : 9758499221", " : 800"},
            {"Doctor Name : Ashutosh Singh", "Hospital Address : Deccan", "Exp : 6yrs", "Mobile No : 7458496214", " : 550"},
            {"Doctor Name : Khelendra Bithle", "Hospital Address : FC Road", "Exp : 7yrs", "Mobile No : 8855471254", " : 500"},
            {"Doctor Name : Ajay Bisen", "Hospital Address : Bavdhan", "Exp : 4yrs", "Mobile No : 7755788954", " : 500"},

    };

    private String[][] doctor_details3 = {
            {"Doctor Name : Ashish Katiwal", "Hospital Address : Kothrud Deppo", "Exp : 3yrs", "Mobile No : 9657655607", " : 500"},
            {"Doctor Name : Prashant Donarkar", "Hospital Address : Karwenagar", "Exp : 6yrs", "Mobile No : 9214254544", " : 800"},
            {"Doctor Name : Naman Soni", "Hospital Address : GM Road", "Exp : 4yrs", "Mobile No : 9148748954", " : 500"},
            {"Doctor Name : Atharwa Sende", "Hospital Address : Gujrat Colony", "Exp : 7yrs", "Mobile No : 8888759624", " : 500"},
            {"Doctor Name : Jayant Kishore", "Hospital Address : Civil Court", "Exp : 4yrs", "Mobile No : 7795788954", " : 500"},
    };

    private String[][] doctor_details4 = {
            {"Doctor Name : Rajan Jha", "Hospital Address : Baner", "Exp : 8yrs", "Mobile No : 9657112407", " : 600"},
            {"Doctor Name : Vashistha Roy", "Hospital Address : Hinjewadi", "Exp : 10yrs", "Mobile No : 9214566944", " : 800"},
            {"Doctor Name : Rohit Kotwal", "Hospital Address : Vishrantwadi", "Exp : 4yrs", "Mobile No : 7795788954", " : 500"},
            {"Doctor Name : Ayush Sharma", "Hospital Address : Wakad", "Exp : 7yrs", "Mobile No : 7884859624", " : 500"},
            {"Doctor Name : Aakash Meshram", "Hospital Address : Hadapsar", "Exp : 4yrs", "Mobile No : 7795228954", " : 500"},

    };

    private String[][] doctor_details5 = {
            {"Doctor Name : Shivanand Yelmeli", "Hospital Address : Hadapsar", "Exp : 8yrs", "Mobile No : 9657112407", " : 600"},
            {"Doctor Name : Navendu Parandkar", "Hospital Address : Hinjewadi", "Exp : 10yrs", "Mobile No : 9214566944", " : 800"},
            {"Doctor Name : Rishi Kherde", "Hospital Address : Vishrantwadi", "Exp : 4yrs", "Mobile No : 7795788954", " : 500"},
            {"Doctor Name : Mayur Pujari", "Hospital Address : Wakad", "Exp : 7yrs", "Mobile No : 7884859624", " : 500"},
            {"Doctor Name : Sachin Chikodi", "Hospital Address : Vishrantwadi", "Exp : 4yrs", "Mobile No : 7795788954", " : 500"},

    };


    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewDDTitle);
        btn = findViewById(R.id.buttonDDBack);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if (title.compareTo("Physician") == 0)
            doctor_details = doctor_details1;
        else
        if (title.compareTo("Dietician") == 0)
            doctor_details = doctor_details2;
        else
        if (title.compareTo("Dentist") == 0)
            doctor_details = doctor_details3;
        else
        if (title.compareTo("Surgeon") == 0)
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for (int i=0; i<doctor_details.length; i++){
            item = new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5", "Cons fees: " +doctor_details[i][4]+"/-");
            list.add( item );
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );
        ListView lst = findViewById(R.id.textViewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this, AppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);
            }
        });
    }
}