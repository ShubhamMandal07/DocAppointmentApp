package com.example.docappointment;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Register extends AppCompatActivity {

    EditText FullName,Email,Password,ConfPass;
    LinearLayout layout;
    Spinner Gender,Role,Type;
    Button Register;
    FirebaseAuth mAuth;
    DatabaseReference PatientDatabase,DoctorDatabase;
    List<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FullName = findViewById(R.id.fullName);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        ConfPass = findViewById(R.id.confirmPassword);
        Gender = findViewById(R.id.gender);
        Role = findViewById(R.id.role);
        layout = findViewById(R.id.SelectDoctorTypeLayout);
        Type = findViewById(R.id.type);
        Register = findViewById(R.id.registerButton);

        items = new ArrayList<>();
        items.add("None");
        items.add("Male");
        items.add("Female");
        items.add("Others");

        Gender.setAdapter(new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,items));



        items = new ArrayList<>();
        items.add("None");
        items.add("Doctor");
        items.add("Patient");

        Role.setAdapter(new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,items));

        items = new ArrayList<>();
        items.add("None");
        items.add("General");
        items.add("Skin");
        items.add("Nose");
        items.add("Heart");
        items.add("Ear");

        Type.setAdapter(new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,items));

        Role.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(Role.getSelectedItem().toString().equals("Doctor"))
                {
                    layout.setVisibility(View.VISIBLE);
                }
                else if(!Role.getSelectedItem().toString().equals("Doctor"))
                {
                    layout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mAuth = FirebaseAuth.getInstance();
        PatientDatabase = FirebaseDatabase.getInstance().getReference("LoginDetails").child("PatientLoginDetails");
        DoctorDatabase =  FirebaseDatabase.getInstance().getReference("LoginDetails").child("DoctorsLoginDetails");

        Register.setOnClickListener(view -> {
            String fullName = FullName.getText().toString();
            String email = Email.getText().toString().trim();
            System.out.println(email);
            String password = Password.getText().toString();
            String confPass = ConfPass.getText().toString();
            Object role = Role.getSelectedItem();
            Object gender = Gender.getSelectedItem();

            RegisterTheUser(fullName,email,password,confPass, (String) role, (String) gender);
        });
    }

    private void RegisterTheUser(String fullName, String email, String password, String confPass, String role, String gender) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            System.out.println(user.getUid());
                            Toast.makeText(getApplicationContext(), "Authentication Success.",
                                    Toast.LENGTH_SHORT).show();

                            LoginDetails loginDetails = new LoginDetails(fullName,email,password,gender,role);
                            if(role.equals("Doctor"))
                            {
                                String type = Type.getSelectedItem().toString();
                                HashMap<String, Object> map=new HashMap<String, Object>();
                                map.put("Type",type);
                                DoctorDatabase.child(user.getUid()).setValue(loginDetails);
                                DoctorDatabase.child(user.getUid()).updateChildren(map);
                            }
                            else if (role.equals("Patient"))
                            {
                                PatientDatabase.child(user.getUid()).setValue(loginDetails);
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            System.out.println(task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }



}