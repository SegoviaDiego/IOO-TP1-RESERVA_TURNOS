package com.company.DAO;

import com.company.Negocio.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorDAO implements FileDAO<Doctor> {
    @Override
    public List<Doctor> findAll() {
        File entityDatabase = this.getFile();
        List data = entityDatabase.get();

        if (data == null) data = new ArrayList<>();

        return data;
    }

    @Override
    public Doctor findById(long id) {
        List<Doctor> data = this.findAll();

        return data.stream().filter(entity -> entity.getId() == id).findAny().orElse(null);
    }


    @Override
    public void create(Doctor entity) {
        List<Doctor> data = this.findAll();

        entity.setId(this.generateId(data));
        data.add(entity);

        this.save(data);
    }

    @Override
    public void update(Doctor entity) {
        List<Doctor> data = this.findAll();

        for (Doctor e : data)
            if (entity.getId() == e.getId())
                DAO.setFields(entity, e);

        this.save(data);
    }

    @Override
    public void delete(Doctor entity) {
        List<Doctor> data = this.findAll();

        data.removeIf(e -> e.getId() == entity.getId());

        this.save(data);
    }

    @Override
    public void save(List<Doctor> data) {
        File entityDatabase = this.getFile();
        entityDatabase.save(data);
    }

    @Override
    public File getFile() {
        return new File("doctor.database.txt");
    }

    private long generateId(List<Doctor> data) {
        long latestId = 0;
        for (Doctor entity : data) {
            if (entity.getId() > latestId)
                latestId = entity.getId();
        }

        return latestId + 1;
    }
}
