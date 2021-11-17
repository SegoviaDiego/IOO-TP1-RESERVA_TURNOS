package com.company.DAO;

import com.company.Negocio.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorDAO implements FileDAO<Doctor> {
    @Override
    public List<Doctor> findAll() {
        File doctorDatabase = this.getFile();
        List<Doctor> data = doctorDatabase.get();

        if (data == null) data = new ArrayList<Doctor>();

        return data;
    }

    @Override
    public Doctor findById(long id) {
        List<Doctor> data = this.findAll();

        return data.stream().filter(doctor -> doctor.getId() == id).findAny().orElse(null);
    }


    @Override
    public void create(Doctor doctor) {
        List<Doctor> data = this.findAll();

        doctor.setId(this.generateId(data));
        data.add(doctor);

        this.save(data);
    }

    @Override
    public void update(Doctor doctor) {
        List<Doctor> data = this.findAll();

        for (Doctor u : data)
            if (doctor.getId() == u.getId())
                DAO.setFields(doctor, u);

        this.save(data);
    }

    @Override
    public void delete(Doctor doctor) {
        List<Doctor> data = this.findAll();

        data.removeIf(u -> u.getId() == doctor.getId());

        this.save(data);
    }

    @Override
    public void save(List<Doctor> data) {
        File doctorDatabase = this.getFile();
        doctorDatabase.save(data);
    }

    @Override
    public File getFile() {
        return new File("doctor.database.txt");
    }

    private long generateId(List<Doctor> data) {
        long latestId = 0;
        for (Doctor doctor : data) {
            if (doctor.getId() > latestId)
                latestId = doctor.getId();
        }

        return latestId + 1;
    }
}
