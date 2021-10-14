package com.company.Entities.Doctor.View;

import javax.swing.*;

public class DoctorView extends JFrame {
    private JPanel panel;

    public DoctorView(String title){
        super(title);

        this.panel = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.panel);
        this.pack();
        this.setVisible(true);
    }
}
