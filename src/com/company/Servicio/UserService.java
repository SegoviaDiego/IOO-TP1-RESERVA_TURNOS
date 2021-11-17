package com.company.Servicio;

import com.company.DAO.UserDAO;
import com.company.Negocio.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserService extends Service<User> {

    public UserService() {
        super(new UserDAO());
    }

    public Optional<User> findUserByUsernameAndPassword(String username, String password) {
        // @TODO: Add data validation.

        List<User> data = entityDAO.findAll();

        return data.stream().filter(user -> (user.getUsername().equals(username) && user.getPassword().equals(password))).findAny();
    }

    public List<User> getAllDoctors() {
        List<User> data = entityDAO.findAll();

        // TODO: Make "doctor" an enum rather than a hardcoded value.
        Stream<User> doctors = data.stream().filter(User::isDoctor);

        return doctors.collect(Collectors.toList());
    }

    public List<User> getAllUsers() {
        List<User> data = entityDAO.findAll();

        // TODO: Make "user" an enum rather than a hardcoded value.
        Stream<User> users = data.stream().filter(User::isPatient);

        return users.collect(Collectors.toList());
    }
}
