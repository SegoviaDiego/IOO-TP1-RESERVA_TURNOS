package com.company;

import com.company.UI.ViewManager;

public class MainController {
    public static void main(String[] args) {
        ViewManager viewManager = new ViewManager();
        viewManager.init();
        viewManager.show();

//        viewManager.goToLoginView();
        viewManager.goToSelectDoctorView();
    }
}
