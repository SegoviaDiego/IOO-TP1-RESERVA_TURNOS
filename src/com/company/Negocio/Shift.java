package com.company.Negocio;

import java.io.Serializable;
import java.util.Date;

public class Shift implements Serializable {
    private long id;
    private long doctorId;
    private Date startsAt;
    private Date endsAt;

    // Related Entities

    private Doctor doctor;
    private Appointment appointments;


    public Shift(long id, long doctorId, Date startsAt, Date endsAt) {
        this.id = 1;
        this.doctorId = doctorId;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Appointment getAppointments() {
        return appointments;
    }

    public void setAppointments(Appointment appointments) {
        this.appointments = appointments;
    }
}
