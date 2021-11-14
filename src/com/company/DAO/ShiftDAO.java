package com.company.DAO;

import com.company.Negocio.Appointment;
import com.company.Negocio.Shift;

import java.util.ArrayList;
import java.util.List;

public class ShiftDAO implements FileDAO<Shift> {
    @Override
    public List<Shift> findAll() {
        File entityDatabase = this.getFile();
        List data = entityDatabase.get();

        if (data == null) data = new ArrayList<>();

        return data;
    }

    @Override
    public Shift findById(long id) {
        List<Shift> data = this.findAll();

        return data.stream().filter(entity -> entity.getId() == id).findAny().orElse(null);
    }

    @Override
    public List<Appointment> findByUserId(long id) {
        return null;
    }


    @Override
    public void create(Shift entity) {
        List<Shift> data = this.findAll();

        entity.setId(this.generateId(data));
        data.add(entity);

        this.save(data);
    }

    @Override
    public void update(Shift entity) {
        List<Shift> data = this.findAll();

        for (Shift e : data)
            if (entity.getId() == e.getId())
                DAO.setFields(entity, e);

        this.save(data);
    }

    @Override
    public void delete(Shift entity) {
        List<Shift> data = this.findAll();

        data.removeIf(e -> e.getId() == entity.getId());

        this.save(data);
    }

    @Override
    public void save(List<Shift> data) {
        File entityDatabase = this.getFile();
        entityDatabase.save(data);
    }

    @Override
    public File getFile() {
        return new File("shift.database.txt");
    }

    private long generateId(List<Shift> data) {
        long latestId = 0;
        for (Shift entity : data) {
            if (entity.getId() > latestId)
                latestId = entity.getId();
        }

        return latestId + 1;
    }
}
