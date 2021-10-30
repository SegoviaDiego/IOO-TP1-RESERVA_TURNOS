package com.company.Negocio;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private String dni;
    private Date createdAt;
    private String username;
    private String password;
    private String role;


    public User(long id, String firstName, String lastName, String address, String dni, String username, String password) {
        this(id, firstName, lastName, address, dni, username, password, new Date());
    }

    public User(long id, String firstName, String lastName, String address, String dni, String username, String password, Date createdAt) {
        this.id = 1;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.dni = dni;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
