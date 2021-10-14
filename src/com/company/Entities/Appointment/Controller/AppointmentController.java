package com.company.Entities.Appointment.Controller;

import com.company.Entities.Appointment.Model.Appointment;
import com.company.Entities.Appointment.View.AppointmentView;

public class AppointmentController {
    private Appointment model;
    private AppointmentView view;

    public AppointmentController(Appointment model, AppointmentView view) {
        this.model = model;
        this.view = view;
    }

    public void refreshView() {
        // @TODO: Call to this.view.refresh(this.model.getFirstName()...)
    }


}
