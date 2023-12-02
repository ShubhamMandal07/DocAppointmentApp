package com.example.docappointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class PatientLogin extends AppCompatActivity {

    EditText Email,Password;
    Button PatientLogin;
    TextView Register;


    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);

        Email = findViewById(R.id.patientEmail);
        Password = findViewById(R.id.patientPassword);
        PatientLogin = findViewById(R.id.patientLoginBtn);
        Register = findViewById(R.id.register);



        mAuth = FirebaseAuth.getInstance();

        Register.setOnClickListener(view -> {startActivity(new Intent(getApplicationContext(),Register.class));});

        PatientLogin.setOnClickListener(view -> {
            String email = Email.getText().toString().trim();
            String password = Password.getText().toString();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(getApplicationContext(), "Authentication Success.",Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(getApplicationContext(), "Authentication failed.",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        });
    }
}