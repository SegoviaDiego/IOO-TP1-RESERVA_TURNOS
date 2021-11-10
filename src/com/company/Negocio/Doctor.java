package com.company.Negocio;

public class Doctor {
    private long id;
    private String credential; // Matricula

    // Related entities

    private User user;
    private Shift shift;

    public Doctor(int id, String firstName, String lastName, String credential) {

        this.id = id;
        this.credential = credential;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }
}
