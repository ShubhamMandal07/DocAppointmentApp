package com.example.docappointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DoctorsHomepage extends AppCompatActivity {
    ArrayList<AppointmentItemList> appointmentItemLists = new ArrayList<>();
    ArrayList<RequestAppointment> requestAppointments = new ArrayList<>();
    RecyclerView recyclerView,recyclerView2;
    RecyclerView.LayoutManager layoutManager,layoutManager2;
    AppointmentAdapter appointmentAdapter;
    AppointmentRequestAdapter appointmentRequestAdapter;

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    DatabaseReference database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_homepage);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#00B0FF"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);

        drawerLayout = findViewById(R.id.my_drawer_layout2);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        appointmentAdapter = new AppointmentAdapter(this,appointmentItemLists);
        recyclerView.setAdapter(appointmentAdapter);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);
        layoutManager2 = new LinearLayoutManager(this);
        appointmentRequestAdapter = new AppointmentRequestAdapter(this,requestAppointments);
        recyclerView2.setAdapter(appointmentRequestAdapter);
        recyclerView2.setLayoutManager(layoutManager2);

        addRequestData();
        addData();
    }

    private void addRequestData() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        database = FirebaseDatabase.getInstance().getReference("LoginDetails");

        database.child("DoctorsLoginDetails").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String id = "id";
                System.out.println(snapshot.child("AppointmentRequest").child("SelectedDoctorId").getValue());
                String name = "Name :   "+ snapshot.child("AppointmentRequest").child("PatientName").getValue().toString();
                String Date = "Date : "+ snapshot.child("AppointmentRequest").child("SelectedDate").getValue().toString();
                String Time = "Time : "+ snapshot.child("AppointmentRequest").child("SelectedTime").getValue().toString();
                String docId = snapshot.child("AppointmentRequest").child("SelectedDoctorId").getValue().toString();
                requestAppointments.add(new RequestAppointment(name,Date,Time,id,docId));
                appointmentRequestAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    public void addData()
    {
        String name = "Name :   "+"shubham";
        String Gender = "Gender : "+"Male";
        String Date = "Date : "+"10/11/2022";
        String Time = "9:30 am";

        appointmentItemLists.add(new AppointmentItemList(name,Gender,Date,Time));
        appointmentItemLists.add(new AppointmentItemList(name,Gender,Date,Time));
        appointmentItemLists.add(new AppointmentItemList(name,Gender,Date,Time));
        appointmentAdapter.notifyDataSetChanged();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}