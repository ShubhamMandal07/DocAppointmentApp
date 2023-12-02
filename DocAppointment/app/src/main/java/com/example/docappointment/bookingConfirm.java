package com.example.docappointment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class bookingConfirm extends AppCompatActivity {

    Handler handler;
    TextView DoctorName,DoctorType,AppointmentDate,AppointmentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookingconfirm);

        DoctorName = findViewById(R.id.doctorNameBook);
        DoctorType = findViewById(R.id.doctorTypeBook);
        AppointmentDate = findViewById(R.id.appoitnmentDateBook);
        AppointmentTime = findViewById(R.id.appoitnmentTimeBook);

        Intent intent  = getIntent();
        String doctorName = intent.getStringExtra("Name");
        String doctorType = intent.getStringExtra("Type");
        String Date = intent.getStringExtra("Date");
        String Time = intent.getStringExtra("Time");

        DoctorName.setText(doctorName);
        DoctorType.setText(doctorType);
        AppointmentDate.setText(Date);
        AppointmentTime.setText(Time);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}