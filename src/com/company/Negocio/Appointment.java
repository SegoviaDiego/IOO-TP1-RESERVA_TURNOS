package com.company.Negocio;

import java.util.Date;

public class Appointment {
    private int id;
    private int doctorId;
    private int userId;
    private Date startsAt; // Datetime de comienzo del turno.
    private Date endsAt; // Datetime de finalización del turno.

    public Appointment(int id, int doctorId, int userId, Date startsAt, Date endsAt) {
        this.id = id;
        this.doctorId = doctorId;
        this.userId = userId;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(Date startsAt) {
        this.startsAt = startsAt;
    }

    public Date getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(Date endsAt) {
        this.endsAt = endsAt;
    }
}
