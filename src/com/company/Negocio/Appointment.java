package com.company.Negocio;

import java.io.Serializable;

public class Appointment implements Serializable {
    private long id;
    private long doctorId;
    private long userId;
    private int year;
    private int month;
    private int day;
    private int startsAt; // Datetime de comienzo del turno.

    // Related Entities

    private User user;

    public Appointment(long id, long doctorId, long userId, int year, int month, int day, int startsAt) {
        this.id = id;
        this.doctorId = doctorId;
        this.userId = userId;
        this.year = year;
        this.month = month;
        this.day = day;
        this.startsAt = startsAt;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(int startsAt) {
        this.startsAt = startsAt;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
