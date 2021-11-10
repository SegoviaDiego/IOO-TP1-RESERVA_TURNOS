package com.company.Servicio;

import com.company.DAO.ShiftDAO;
import com.company.Negocio.Shift;

import java.util.List;

public class ShiftService extends Service<Shift> {

    public ShiftService() {
        super(new ShiftDAO());
    }

    public List<Shift> findAll() {
        return entityDAO.findAll();
    }

    public Shift findById(long id) {
        return entityDAO.findById(id);
    }

    public void deleteById(long id) {
        Shift entity = this.findById(id);
        entityDAO.delete(entity);
    }

    public void create(Shift entity) {
        entityDAO.create(entity);
    }

    public void update(Shift entity) {
        entityDAO.update(entity);
    }
}
