package com.company.Servicio;

import com.company.DAO.FileDAO;

import java.util.List;

public abstract class Service<T> {
    protected FileDAO<T> entityDAO;

    Service(FileDAO<T> dao) {
        entityDAO = dao;
    }

    public List<T> findAll() {
        return this.entityDAO.findAll();
    }


    public T findById(long id) {
        return this.entityDAO.findById(id);
    }


    public void create(T t) {
        this.entityDAO.create(t);
    }


    public void update(T t) {
        this.entityDAO.update(t);
    }


    public void delete(T t) {
        this.delete(t);
    }


}
