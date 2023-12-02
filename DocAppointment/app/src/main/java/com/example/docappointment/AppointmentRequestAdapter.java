package com.example.docappointment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AppointmentRequestAdapter extends RecyclerView.Adapter<AppointmentRequestAdapter.RequestViewHolder> {

    ArrayList<RequestAppointment> requestAppointments;
    Context context;

    public AppointmentRequestAdapter(Context context, ArrayList<RequestAppointment> requestAppointments) {
        this.requestAppointments = requestAppointments;
        this.context =context;
    }




    public static class RequestViewHolder extends RecyclerView.ViewHolder{

        TextView patientName,  appointmentDate, appointmentTime;


        public RequestViewHolder(@NonNull View itemView2) {
            super(itemView2);
            patientName = itemView2.findViewById(R.id.patientNameFromDatabase);
            appointmentDate = itemView2.findViewById(R.id.AddDateFromDatabase);
            appointmentTime = itemView2.findViewById(R.id.AddTimeFormDatabase);

        }
    }
    @NonNull
    @Override
    public AppointmentRequestAdapter.RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_request,parent,false);
        return new RequestViewHolder(itemView2);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentRequestAdapter.RequestViewHolder holder, int position) {
        holder.patientName.setText(requestAppointments.get(position).getPatientName());
        holder.appointmentDate.setText(requestAppointments.get(position).getAppointmentDate());
        holder.appointmentTime.setText(requestAppointments.get(position).getAppointmentTime());
    }

    @Override
    public int getItemCount() {
        return requestAppointments.size();
    }
}
