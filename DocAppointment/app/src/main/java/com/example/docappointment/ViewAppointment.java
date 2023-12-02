package com.example.docappointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewAppointment extends AppCompatActivity {
    ArrayList<RequestAppointment> requestAppointments = new ArrayList<>();
    RecyclerView recyclerView2;
    RecyclerView.LayoutManager layoutManager2;
    PatientViewAppointmentAdapter patientViewAppointmentAdapter;

    ImageButton Edit, cancel;
    RelativeLayout TextAppointment;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);

        recyclerView2 = findViewById(R.id.viewAppointmentRecyclerView);
        recyclerView2.setHasFixedSize(true);
        layoutManager2 = new LinearLayoutManager(this);
        patientViewAppointmentAdapter = new PatientViewAppointmentAdapter(this,requestAppointments);
        recyclerView2.setAdapter(patientViewAppointmentAdapter);
        recyclerView2.setLayoutManager(layoutManager2);

        TextAppointment = findViewById(R.id.noAppointmentBookLayout);

        patientViewAppointmentAdapter.setOnItemClickListener(position -> gotoItemActivity(position));
        patientViewAppointmentAdapter.cancelappointment(position -> cancelAppointment(position));

        addData();
    }
    private void cancelAppointment(int position)
    {
        String AppointmentId =  requestAppointments.get(position).getAppointmentId();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("LoginDetails").child("PatientLoginDetails").child(user.getUid())
                .child("MyAppointments").child(AppointmentId);
        databaseReference.removeValue();
        String doctorid = requestAppointments.get(position).getDoctorsKey();
        databaseReference = FirebaseDatabase.getInstance().getReference("LoginDetails").child("DoctorsLoginDetails").
                child(doctorid).child("AppointmentRequest").child(user.getUid()).child(AppointmentId);
        databaseReference.removeValue();
        requestAppointments.remove(position);


        finish();
        patientViewAppointmentAdapter.notifyDataSetChanged();
        startActivity(new Intent(getApplicationContext(),ViewAppointment.class));

        if (requestAppointments.isEmpty())
        {
            TextAppointment.setVisibility(View.VISIBLE);
        }

    }


    private void gotoItemActivity(int position) {
        Intent intent = new Intent(this,MakeAppointment.class);
        intent.putExtra("Name",requestAppointments.get(position).getPatientName());
        intent.putExtra("Date",requestAppointments.get(position).getAppointmentDate());
        intent.putExtra("Time",requestAppointments.get(position).getAppointmentTime());
        intent.putExtra("position",position);
        startActivity(intent);
    }

    public void addData()
    {

        databaseReference = FirebaseDatabase.getInstance().getReference("LoginDetails").child("PatientLoginDetails");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        databaseReference.child(user.getUid()).child("MyAppointments").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren())
                {
                    String id = snap.getKey();
                    String name = "Doctors Name :   "+ snap.child("SelectedDoctor").getValue().toString();
                    String Date = "Date : "+ snap.child("SelectedDate").getValue().toString();
                    String Time = "Time : "+ snap.child("SelectedTime").getValue().toString();
                    System.out.println(snap.child("SelectedDoctorId"));
                    String docId = snap.child("SelectedDoctorId").getValue().toString();
                    requestAppointments.add(new RequestAppointment(name,Date,Time,id,docId));
                    patientViewAppointmentAdapter.notifyDataSetChanged();
                    if (!requestAppointments.isEmpty())
                    {
                        TextAppointment.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






    }
}