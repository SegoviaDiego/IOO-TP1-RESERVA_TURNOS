package com.company;

import com.company.Negocio.User;
import com.company.Servicio.UserService;
import com.company.UI.ViewManager;

public class MainController {
    public static void main(String[] args) {
        ViewManager viewManager = new ViewManager();
        viewManager.init();
        viewManager.show();

//        viewManager.goToLoginView();
        // TODO: Remove before PR. Uncomment.
        viewManager.goToSelectDoctorView();
    }
}
