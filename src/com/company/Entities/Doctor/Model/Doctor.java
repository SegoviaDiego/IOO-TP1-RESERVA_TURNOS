package com.company.Entities.Doctor.Model;

import java.util.Date;

public class Doctor {
    private int id;
    private String firstName; // Nombre
    private String lastName; // Apellido
    private String credential; // Matricula
    private Date startsWorkingTime; // Comienzo de turno.
    private Date endsWorkingTime; // Fin de turno.

    public Doctor(int id, String firstName, String lastName, String credential){

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.credential = credential;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }
}
