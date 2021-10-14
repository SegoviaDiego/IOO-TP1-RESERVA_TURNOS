package com.company.Entities.Doctor.Controller;

import com.company.Entities.Doctor.Model.Doctor;
import com.company.Entities.Doctor.View.DoctorView;

public class DoctorController {
    private Doctor model;
    private DoctorView view;

    public DoctorController(Doctor model, DoctorView view) {
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
        // @TODO: Call to this.view.refresh(this.model.getFirstName()...)
    }


}
