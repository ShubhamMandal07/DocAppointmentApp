package com.example.docappointment;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {
    ArrayList<AppointmentItemList>appointmentItemLists;
    Context context;

    public AppointmentAdapter(Context context, ArrayList<AppointmentItemList> appointmentItemLists) {
        this.appointmentItemLists = appointmentItemLists;
        this.context =context;
    }
    public static class AppointmentViewHolder extends RecyclerView.ViewHolder{

        TextView patientName, patientGender, appointmentDate, appointmentTime;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            patientName = itemView.findViewById(R.id.patientNameFromDatabase);
            patientGender = itemView.findViewById(R.id.patientGenderFromDatabase);
            appointmentDate = itemView.findViewById(R.id.AddDateFromDatabase);
            appointmentTime = itemView.findViewById(R.id.AddTimeFormDatabase);

        }
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_item,parent,false);
        return new AppointmentViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {

        holder.patientName.setText(appointmentItemLists.get(position).getPatientName());
        holder.patientGender.setText(appointmentItemLists.get(position).getPatientGender());
        holder.appointmentDate.setText(appointmentItemLists.get(position).getPatientAppointmentDate());
        holder.appointmentTime.setText(appointmentItemLists.get(position).getPatientAppointmentTime());
    }

    @Override
    public int getItemCount() {
        return appointmentItemLists.size();
    }
}
