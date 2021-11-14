package com.company.DAO;

import com.company.Negocio.Appointment;
import com.company.Negocio.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO implements FileDAO<User> {
    @Override
    public List<User> findAll() {
        File userDatabase = this.getFile();
        List<User> data = userDatabase.get();

        if (data == null) data = new ArrayList<User>();

        return data;
    }

    @Override
    public User findById(long id) {
        List<User> data = this.findAll();

        return data.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    @Override
    public List<Appointment> findByUserId(long id) {
        return null;
    }


    @Override
    public void create(User user) {
        List<User> data = this.findAll();

        // Si no existe un usuario con el mismo Username.
        if (data.stream().noneMatch(u -> u.getUsername() == user.getUsername())) {
            user.setId(this.generateId(data));
            data.add(user);

            this.save(data);
        }
    }

    @Override
    public void update(User user) {
        List<User> data = this.findAll();

        for (User u : data)
            if (user.getId() == u.getId())
                DAO.setFields(user, u);

        this.save(data);
    }

    @Override
    public void delete(User user) {
        List<User> data = this.findAll();

        data.removeIf(u -> u.getId() == user.getId());

        this.save(data);
    }

    @Override
    public void save(List<User> data) {
        File userDatabase = this.getFile();
        userDatabase.save(data);
    }

    @Override
    public File getFile() {
        return new File("user.database.txt");
    }

    private long generateId(List<User> data) {
        long latestId = 0;
        for (User user : data) {
            if (user.getId() > latestId)
                latestId = user.getId();
        }

        return latestId + 1;
    }
}
