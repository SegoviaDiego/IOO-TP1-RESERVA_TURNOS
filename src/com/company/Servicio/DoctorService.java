package com.company.Servicio;

import com.company.DAO.DoctorDAO;
import com.company.Negocio.Doctor;

import java.util.List;

public class DoctorService extends Service<Doctor> {

    public DoctorService() {
        super(new DoctorDAO());
    }

    public List<Doctor> findAll() {
        return entityDAO.findAll();
    }

    public Doctor findById(long id) {
        return entityDAO.findById(id);
    }

    public void deleteById(long id) {
        Doctor entity = this.findById(id);
        entityDAO.delete(entity);
    }

    public void create(Doctor entity) {
        entityDAO.create(entity);
    }

    public void update(Doctor entity) {
        entityDAO.update(entity);
    }
}
