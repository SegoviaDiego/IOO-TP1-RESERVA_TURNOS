package com.company.Negocio;

import java.util.Date;

public class Doctor {
    private int id;
    private String credential; // Matricula
    private Date startsWorkingTime; // Comienzo de turno.
    private Date endsWorkingTime; // Fin de turno.

    public Doctor(int id, String firstName, String lastName, String credential){

        this.id = id;
        this.credential = credential;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }
}
