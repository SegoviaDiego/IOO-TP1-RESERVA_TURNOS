package com.company.Negocio;

import java.io.Serializable;

public class Doctor implements Serializable {
    private long id;
    private long userId;
    private String credential; // Matricula
    private int startsAt;
    private int endsAt;

    // Related entities

    private User user;

    public Doctor(long id, long userId, String credential, int startsAt, int endsAt) {
        this.id = id;
        this.userId = userId;
        this.credential = credential;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(int startsAt) {
        this.startsAt = startsAt;
    }

    public int getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(int endsAt) {
        this.endsAt = endsAt;
    }
}
