package com.company.Servicio;

import com.company.DAO.UserDAO;
import com.company.Negocio.User;

import java.util.List;
import java.util.Optional;

public class UserService extends Service<User> {

    public UserService() {
        super(new UserDAO());
    }

    public Optional<User> findUserByUsernameAndPassword(String username, String password) {
        // @TODO: Add data validation.

        List<User> data = entityDAO.findAll();

        return data.stream().filter(user -> (user.getUsername() == username && user.getPassword() == password)).findAny();
    }
}
