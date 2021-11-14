package com.company.DAO;

import com.company.Negocio.Appointment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentDAO implements FileDAO<Appointment> {
    @Override
    public List<Appointment> findAll() {
        File entityDatabase = this.getFile();
        List data = entityDatabase.get();

        if (data == null) data = new ArrayList<>();

        return data;
    }

    @Override
    public Appointment findById(long id) {
        List<Appointment> data = this.findAll();

        return data.stream().filter(entity -> entity.getId() == id).findAny().orElse(null);
    }

    public List<Appointment> findByUserId(long userId) {
        List<Appointment> data = this.findAll();

        return data.stream().filter(entity -> entity.getUser().getId() == userId).collect(Collectors.toList());
    }


    @Override
    public void create(Appointment entity) {
        List<Appointment> data = this.findAll();

        entity.setId(this.generateId(data));
        data.add(entity);

        this.save(data);
    }

    @Override
    public void update(Appointment entity) {
        List<Appointment> data = this.findAll();

        for (Appointment e : data)
            if (entity.getId() == e.getId())
                DAO.setFields(entity, e);

        this.save(data);
    }

    @Override
    public void delete(Appointment entity) {
        List<Appointment> data = this.findAll();

        data.removeIf(e -> e.getId() == entity.getId());

        this.save(data);
    }

    @Override
    public void save(List<Appointment> data) {
        File entityDatabase = this.getFile();
        entityDatabase.save(data);
    }

    @Override
    public File getFile() {
        return new File("appointment.database.txt");
    }

    private long generateId(List<Appointment> data) {
        long latestId = 0;
        for (Appointment entity : data) {
            if (entity.getId() > latestId)
                latestId = entity.getId();
        }

        return latestId + 1;
    }
}
