package com.company.Entities.User.Controller;

import com.company.Entities.User.Model.User;
import com.company.Entities.User.View.UserView;

public class UserController {
    private User model;
    private UserView view;

    public UserController(User model, UserView view) {
        this.model = model;
        this.view = view;
    }

    public String getFirstName() {
        return this.model.getFirstName();
    }

    public void setFirstName(String firstName) {
        this.model.setFirstName(firstName);
    }

    public void refreshView() {
        // @TODO: Call to this.view.refresh(this.user.getName()...)
    }


}
