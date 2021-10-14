package com.company.Entities.Appointment.View;

import javax.swing.*;

public class AppointmentView extends JFrame {
    private JPanel panel;

    public AppointmentView(String title){
        super(title);

        this.panel = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.panel);
        this.pack();
        this.setVisible(true);
    }
}
