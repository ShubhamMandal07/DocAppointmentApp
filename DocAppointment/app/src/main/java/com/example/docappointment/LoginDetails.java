package com.example.docappointment;

public class LoginDetails {
    String FullName;
    String Email;
    String Password;
    String Gender;
    String Role;

    public LoginDetails(String fullName, String email, String password, String gender, String role) {
        FullName = fullName;
        Email = email;
        Password = password;
        Gender = gender;
        Role = role;
    }

    public String getFullName() {
        return FullName;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getGender() {
        return Gender;
    }

    public String getRole() {
        return Role;
    }
}
