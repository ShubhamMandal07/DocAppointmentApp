package com.example.docappointment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PatientViewAppointmentAdapter extends RecyclerView.Adapter<PatientViewAppointmentAdapter.ViewHolder> {
    static ArrayList<RequestAppointment> requestAppointments;
    Context context;
    ArrayList<RequestAppointment> requestAppointments1 = new ArrayList<>();
    static public ImageButton EditAppointment,CancelAppointment;

    private static OnItemClickListener onItemClickListener,cancelAppointment;
    public interface OnItemClickListener{
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        PatientViewAppointmentAdapter.onItemClickListener = onItemClickListener;
    }

    public interface test{
        void onClick(int position);
    }
    public void cancelappointment(OnItemClickListener onItemClickListener) {
        PatientViewAppointmentAdapter.cancelAppointment =  onItemClickListener;
    }

    public PatientViewAppointmentAdapter(ViewAppointment context, ArrayList<RequestAppointment> requestAppointments) {
        PatientViewAppointmentAdapter.requestAppointments = requestAppointments;
        this.context =context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView patientName,  appointmentDate, appointmentTime;
        ArrayList<RequestAppointment> requestAppointments;

        public ViewHolder(@NonNull View itemView2) {
            super(itemView2);
            patientName = itemView2.findViewById(R.id.DoctorsNameFromDatabase);
            appointmentDate = itemView2.findViewById(R.id.AddDate);
            appointmentTime = itemView2.findViewById(R.id.AppointmentTime);

            EditAppointment = itemView2.findViewById(R.id.EditAppointment);
            CancelAppointment = itemView2.findViewById(R.id.CancelAppointment);

            EditAppointment.setOnClickListener(v->onItemClickListener.onClick(getAdapterPosition()));
            CancelAppointment.setOnClickListener(v->cancelAppointment.onClick(getAdapterPosition()));
        }


    }


    @NonNull
    @Override
    public PatientViewAppointmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_view,parent,false);
        return new PatientViewAppointmentAdapter.ViewHolder(itemView2);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewAppointmentAdapter.ViewHolder holder, int position) {

        holder.patientName.setText(requestAppointments.get(position).getPatientName());
        holder.appointmentDate.setText(requestAppointments.get(position).getAppointmentDate());
        holder.appointmentTime.setText(requestAppointments.get(position).getAppointmentTime());

    }

    @Override
    public int getItemCount() {
        return requestAppointments.size();
    }


}



