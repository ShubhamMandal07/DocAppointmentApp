package com.example.docappointment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class TimePickerDialog extends DialogFragment {

    public static final String TIME_ADD_DIALOG = "addTime";

    private SendData listener;


    Button button;
    DatabaseReference databaseReference;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = null;
        if(getTag().equals(TIME_ADD_DIALOG))dialog=getTimePickerDialog();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        return dialog;
    }

    private Dialog getTimePickerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.timepicker_dialog,null);
        Bundle bundle = getArguments();
        String SelectedDoctor = bundle.getString("NameOftheDoctor","");
        String SelectedDate = bundle.getString("SelectedDate","");
       // System.out.println(SelectedDoctor);
       // System.out.println(SelectedDate);

        Button button1 = view.findViewById(R.id.time10_1030);
        Button button2 = view.findViewById(R.id.time1030_11);
        Button button3 = view.findViewById(R.id.time11_1130);
        Button button4 = view.findViewById(R.id.time1130_12);
        Button button5 = view.findViewById(R.id.time3_330);
        Button button6 = view.findViewById(R.id.time330_4);
        Button button7 = view.findViewById(R.id.time4_430);
        Button button8 = view.findViewById(R.id.time430_5);

        databaseReference = FirebaseDatabase.getInstance().getReference("LoginDetails").child("DoctorsLoginDetails");

        button1.setOnClickListener(v -> {listener.applyText(button1.getText().toString());dismiss();});
        button2.setOnClickListener(v -> {listener.applyText(button2.getText().toString());dismiss();});
        button3.setOnClickListener(v -> {listener.applyText(button3.getText().toString());dismiss();});
        button4.setOnClickListener(v -> {listener.applyText(button4.getText().toString());dismiss();});
        button5.setOnClickListener(v -> {listener.applyText(button5.getText().toString());dismiss();});
        button6.setOnClickListener(v -> {listener.applyText(button6.getText().toString());dismiss();});
        button7.setOnClickListener(v -> {listener.applyText(button7.getText().toString());dismiss();});
        button8.setOnClickListener(v -> {listener.applyText(button8.getText().toString());dismiss();});

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("UseCompatLoadingForColorStateLists")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren())
                {
                    //System.out.println(snapshot.child(snap.getKey()).getValue());
                    if (SelectedDoctor.equals(snapshot.child(snap.getKey()).child("fullName").getValue().toString()))
                    {
                        //System.out.println(snapshot.child(snap.getKey()).child("AppointmentRequest"));
                        for (DataSnapshot MainData : snapshot.child(snap.getKey()).child("AppointmentRequest").getChildren())
                        {
                            //System.out.println(MainData);
                            for (DataSnapshot FinalData : MainData.getChildren())
                            {
                                System.out.println(MainData.child(FinalData.getKey()).child("SelectedDate"));
                                if (SelectedDate.equals(MainData.child(FinalData.getKey()).child("SelectedDate").getValue().toString()))
                                {
                                    System.out.println(MainData.child(FinalData.getKey()).child("SelectedTime").getValue().toString());
                                    String SelectedTime = MainData.child(FinalData.getKey()).child("SelectedTime").getValue().toString();

                                    if (button1.getText().toString().equals(SelectedTime) )
                                    {
                                        button1.setBackgroundTintList(button1.getResources().getColorStateList(R.color.red));
                                        button1.setTextColor(Color.WHITE);
                                        button1.setOnClickListener(v -> {Toast.makeText(getContext(),"This Time Slot is already Booked Please Select a Diffrent one",Toast.LENGTH_SHORT).show();});
                                    }


                                    if (button2.getText().toString().equals(SelectedTime) )
                                    {
                                        button2.setBackgroundTintList(button2.getResources().getColorStateList(R.color.red));
                                        button2.setTextColor(Color.WHITE);
                                        button2.setOnClickListener(v -> {Toast.makeText(getContext(),"This Time Slot is already Booked Please Select a Diffrent one",Toast.LENGTH_SHORT).show();});
                                    }


                                    if (button3.getText().toString().equals(SelectedTime) )
                                    {
                                        button3.setBackgroundTintList(button3.getResources().getColorStateList(R.color.red));
                                        button3.setTextColor(Color.WHITE);
                                        button3.setOnClickListener(v -> {Toast.makeText(getContext(),"This Time Slot is already Booked Please Select a Diffrent one",Toast.LENGTH_SHORT).show();});
                                    }

                                    if (button4.getText().toString().equals(SelectedTime) )
                                    {
                                        button4.setBackgroundTintList(button4.getResources().getColorStateList(R.color.red));
                                        button4.setTextColor(Color.WHITE);
                                        button4.setOnClickListener(v -> {Toast.makeText(getContext(),"This Time Slot is already Booked Please Select a Diffrent one",Toast.LENGTH_SHORT).show();});
                                    }

                                    if (button5.getText().toString().equals(SelectedTime) )
                                    {
                                        button5.setBackgroundTintList(button5.getResources().getColorStateList(R.color.red));
                                        button5.setTextColor(Color.WHITE);
                                        button5.setOnClickListener(v -> {Toast.makeText(getContext(),"This Time Slot is already Booked Please Select a Diffrent one",Toast.LENGTH_SHORT).show();});
                                    }

                                    if (button6.getText().toString().equals(SelectedTime) )
                                    {
                                        button6.setBackgroundTintList(button6.getResources().getColorStateList(R.color.red));
                                        button6.setTextColor(Color.WHITE);
                                        button6.setOnClickListener(v -> {Toast.makeText(getContext(),"This Time Slot is already Booked Please Select a Diffrent one",Toast.LENGTH_SHORT).show();});
                                    }

                                    if (button7.getText().toString().equals(SelectedTime) )
                                    {
                                        button7.setBackgroundTintList(button7.getResources().getColorStateList(R.color.red));
                                        button7.setTextColor(Color.WHITE);
                                        button7.setOnClickListener(v -> {Toast.makeText(getContext(),"This Time Slot is already Booked Please Select a Diffrent one",Toast.LENGTH_SHORT).show();});
                                    }

                                    if (button8.getText().toString().equals(SelectedTime) )
                                    {
                                        button8.setBackgroundTintList(button8.getResources().getColorStateList(R.color.red));
                                        button8.setTextColor(Color.WHITE);
                                        button8.setOnClickListener(v -> {Toast.makeText(getContext(),"This Time Slot is already Booked Please Select a Diffrent one",Toast.LENGTH_SHORT).show();});
                                    }

                                }
                            }
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (SendData) context;
        } catch (ClassCastException e) {
           throw new ClassCastException(context.toString() + "must implemnet SendData");
        }
    }

    public interface SendData{
        void applyText(String TimeMain);
    }
}