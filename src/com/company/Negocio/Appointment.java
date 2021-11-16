package com.company.Negocio;

import java.util.Date;

public class Appointment {
    private long id;
    private int doctorId;
    private int userId;
    private Date scheduledFor; // Datetime de comienzo del turno.

    // Related Entities

    private User user;
    private Shift shift;
    private Doctor doctor;

    public Appointment(){}

    public Appointment(int id, int doctorId, int userId, Date scheduledFor) {
        this.id = id;
        this.doctorId = doctorId;
        this.userId = userId;
        this.scheduledFor = scheduledFor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public Date getScheduledFor() {
        return scheduledFor;
    }

    public void setScheduledFor(Date scheduledFor) {
        this.scheduledFor = scheduledFor;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
