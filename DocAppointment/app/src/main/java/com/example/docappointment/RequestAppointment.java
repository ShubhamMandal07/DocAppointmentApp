package com.example.docappointment;

import android.widget.ImageButton;

public class RequestAppointment {
    private String PatientName,AppointmentDate,AppointmentTime,AppointmentId,DoctorsKey;

    public String getDoctorsKey() {
        return DoctorsKey;
    }

    public void setDoctorsKey(String doctorsKey) {
        DoctorsKey = doctorsKey;
    }

    public String getAppointmentId() {
        return AppointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        AppointmentId = appointmentId;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getAppointmentDate() {
        return AppointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        AppointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return AppointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        AppointmentTime = appointmentTime;
    }

    public RequestAppointment(String patientName, String appointmentDate, String appointmentTime,String appointmentId,String doctorsKey) {
        PatientName = patientName;
        AppointmentDate = appointmentDate;
        AppointmentTime = appointmentTime;
        AppointmentId = appointmentId;
        DoctorsKey = doctorsKey;

    }
}
