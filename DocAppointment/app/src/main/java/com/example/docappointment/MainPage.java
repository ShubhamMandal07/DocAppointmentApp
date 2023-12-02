package com.example.docappointment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainPage extends AppCompatActivity {

    ImageButton DoctorButton , PateintButton;
    TextView RegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        DoctorButton = findViewById(R.id.doctorButton);
        PateintButton = findViewById(R.id.patientButton);
        RegisterButton = findViewById(R.id.register);


        DoctorButton.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),DoctorLogin.class));
        });
        PateintButton.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),PatientLogin.class));
        });
        RegisterButton.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),Register.class));
        });
    }
}