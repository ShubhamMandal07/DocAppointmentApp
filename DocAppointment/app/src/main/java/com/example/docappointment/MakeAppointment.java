package com.example.docappointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.health.SystemHealthManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ObjectStreamException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

public class MakeAppointment extends AppCompatActivity implements TimePickerDialog.SendData {

    TextView dateMP, timeMP;

    EditText PatientName;
    int year, day, month;
    int hour, minutes;
    Spinner DoctorType,Type;
    List<String> items;
    Button makeAppointment;
    LinearLayout DoctorLayout;

    DatabaseReference database;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_appointment);



        dateMP = findViewById(R.id.dateMP);
        Calendar calendar = Calendar.getInstance();

        timeMP = findViewById(R.id.timeMP);
        DoctorLayout = findViewById(R.id.ShowDoctorsByType);

        PatientName = findViewById(R.id.patientNameMA);

        DoctorType = findViewById(R.id.genderMP);
        Type = findViewById(R.id.typeMP);
        makeAppointment = findViewById(R.id.makeAppointmentBtn);

        database = FirebaseDatabase.getInstance().getReference("LoginDetails");

        items = new ArrayList<>();
        items.add("None");
        items.add("General");
        items.add("Skin");
        items.add("Nose");
        items.add("Heart");
        items.add("Ear");


        Type.setAdapter(new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,items));

        ArrayList<String> DoctorsList = new ArrayList<>();
        DoctorsList.add("None");

        Type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                database.child("DoctorsLoginDetails").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DoctorsList.clear();
                        DoctorsList.add("None");
                        DoctorType.setSelection(0);

                        String DoctorType  = Type.getSelectedItem().toString();
                        for (DataSnapshot snap : snapshot.getChildren())
                        {
                            String key = snap.getKey();
                            if (DoctorType.equals(snapshot.child(key).child("Type").getValue())) {

                                System.out.println(snapshot.child(key).child("fullName").getValue());
                                DoctorsList.add(snapshot.child(key).child("fullName").getValue().toString());
                                DoctorLayout.setVisibility(View.VISIBLE);
                            }
                            
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        DoctorType.setAdapter(new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,DoctorsList));

        timeMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.example.docappointment.TimePickerDialog timePickerDialog = new com.example.docappointment.TimePickerDialog();
                String SelectedDoctor = DoctorType.getSelectedItem().toString();
                String SelectedDate = dateMP.getText().toString();


                Bundle bundle = new Bundle();
                bundle.putString("NameOftheDoctor",SelectedDoctor);
                bundle.putString("SelectedDate",SelectedDate);
                timePickerDialog.setArguments(bundle);
                timePickerDialog.show(getSupportFragmentManager(), TimePickerDialog.TIME_ADD_DIALOG);

            }


        });


        dateMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MakeAppointment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,int monthOfYear, int dayOfMonth) {
                        String date = String.valueOf(year + monthOfYear + dayOfMonth);
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        dateMP.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        });

        Intent intent  = getIntent();
        String Name = intent.getStringExtra("Name");
        String Date = intent.getStringExtra("Date");
        String Time = intent.getStringExtra("Time");
        int position = intent.getIntExtra("position",-1);
        if (getIntent().hasExtra("Name"))
        {
            System.out.println("True");
            System.out.println(Name);
            PatientName.setText(Name);
            System.out.println(Date);
            dateMP.setText(Date);
            System.out.println(Time);
            timeMP.setText(Time);
        }
        else {
            System.out.println("TimeMain");
        }

        makeAppointment.setOnClickListener(view -> {

            String patientName = PatientName.getText().toString();
            String SelectedDate = dateMP.getText().toString();
            String SelectedTime = timeMP.getText().toString();
            String SelectedType = Type.getSelectedItem().toString();
            String SelectedDoctor = DoctorType.getSelectedItem().toString();


            System.out.println(patientName + " " + SelectedDate+ " " +SelectedTime+ " " +SelectedType+ " " +SelectedDoctor);
            HashMap<String , Object> map = new HashMap<>();
            database.child("DoctorsLoginDetails").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {


                    for (DataSnapshot snap : snapshot.getChildren())
                    {
                        String key = snap.getKey();
                        if (SelectedDoctor.equals(snapshot.child(key).child("fullName").getValue())) {

                            String DoctorsKey = snap.getKey();

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            map.put("PatientName",patientName);
                            map.put("SelectedDate",SelectedDate);
                            map.put("SelectedTime",SelectedTime);
                            map.put("SelectedType",SelectedType);
                            map.put("SelectedDoctor",SelectedDoctor);
                            map.put("SelectedDoctorId", DoctorsKey);


                            database.child("PatientLoginDetails").child(user.getUid()).child("MyAppointments").child(patientName + " "+ SelectedDate + SelectedTime).setValue(map);

                            map.put("PatientID",user.getUid());
                            map.put("Status"," ");
                            database.child("DoctorsLoginDetails").child(DoctorsKey).child("AppointmentRequest").child(user.getUid()).child(patientName+ " "+ SelectedDate + SelectedTime).setValue(map);
                            BookingConfirm(map);
                        }

                    }

                }

                private void BookingConfirm(HashMap<String, Object> map) {
                    Intent intent = new Intent(getApplicationContext(),bookingConfirm.class);
                    intent.putExtra("Name", Objects.requireNonNull(map.get("SelectedDoctor")).toString());
                    intent.putExtra("Type", Objects.requireNonNull(map.get("SelectedType")).toString());
                    intent.putExtra("Date", Objects.requireNonNull(map.get("SelectedDate")).toString());
                    intent.putExtra("Time", Objects.requireNonNull(map.get("SelectedTime")).toString());
                    //startActivity(intent);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });





        });

    }



    @Override
    public void applyText(String TimeMain) {
        timeMP.setText(TimeMain);
    }



}