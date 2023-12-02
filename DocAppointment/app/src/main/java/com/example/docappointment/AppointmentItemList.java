package com.example.docappointment;

public class AppointmentItemList {
    private String PatientName;
    private String PatientGender;
    private String PatientAppointmentDate;
    private String PatientAppointmentTime;

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getPatientGender() {
        return PatientGender;
    }

    public void setPatientGender(String patientGender) {
        PatientGender = patientGender;
    }

    public String getPatientAppointmentDate() {
        return PatientAppointmentDate;
    }

    public void setPatientAppointmentDate(String patientAppointmentDate) {
        PatientAppointmentDate = patientAppointmentDate;
    }

    public String getPatientAppointmentTime() {
        return PatientAppointmentTime;
    }

    public void setPatientAppointmentTime(String patientAppointmentTime) {
        PatientAppointmentTime = patientAppointmentTime;
    }

    public AppointmentItemList(String patientName, String patientGender, String patientAppointmentDate, String patientAppointmentTime) {
        PatientName = patientName;
        PatientGender = patientGender;
        PatientAppointmentDate = patientAppointmentDate;
        PatientAppointmentTime = patientAppointmentTime;
    }
}
