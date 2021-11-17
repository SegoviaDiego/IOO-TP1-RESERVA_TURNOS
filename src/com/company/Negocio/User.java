package com.company.Negocio;

import com.company.Servicio.DoctorService;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

enum UserRole {
    DOCTOR,
    PATIENT
}

public class User implements Serializable {
    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private String dni;
    private Date createdAt;
    private String username;
    private String password;
    private UserRole role;

    // Related Entities

    private Doctor doctor;
    private Appointment appointments;

    public User(long id, String firstName, String lastName, String address, String dni, String username, String password) {
        this(id, firstName, lastName, address, dni, username, password, UserRole.PATIENT);
    }

    public User(long id, String firstName, String lastName, String address, String dni, String username, String password, UserRole role) {
        this(id, firstName, lastName, address, dni, username, password, role, new Date());
    }

    public User(long id, String firstName, String lastName, String address, String dni, String username, String password, UserRole role, Date createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.dni = dni;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
        this.role = role;
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
        return role.toString();
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Doctor getDoctor() {
        if (doctor == null) {
            DoctorService doctorService = new DoctorService();
            List<Doctor> dl = doctorService.findAll();

            Optional<Doctor> doc = dl.stream().filter(d -> d.getUserId() == this.getId()).findFirst();

            doc.ifPresent(value -> doctor = value);
        }

        return doctor;
    }

    public Appointment getAppointments() {
        return appointments;
    }

    public void setAppointments(Appointment appointments) {
        this.appointments = appointments;
    }

    public void makePatient() {
        this.role = UserRole.PATIENT;
    }

    public void makeDoctor() {
        this.role = UserRole.DOCTOR;
    }

    public boolean isPatient() {
        return this.role.equals(UserRole.PATIENT);
    }

    public boolean isDoctor() {
        return this.role.equals(UserRole.DOCTOR);
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
