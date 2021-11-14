package com.company.Servicio;

import com.company.DAO.AppointmentDAO;
import com.company.Negocio.Appointment;

import java.util.List;

public class AppointmentService extends Service<Appointment> {

    public AppointmentService() {
        super(new AppointmentDAO());
    }

    public List<Appointment> findAll() {
        return entityDAO.findAll();
    }

    public Appointment findById(long id) {
        return entityDAO.findById(id);
    }

    public List<Appointment> findByUserId(long id){
        return entityDAO.findByUserId(id);
    }

    public void deleteById(long id) {
        Appointment entity = this.findById(id);
        entityDAO.delete(entity);
    }

    public void create(Appointment entity) {
        entityDAO.create(entity);
    }

    public void update(Appointment entity) {
        entityDAO.update(entity);
    }
}
