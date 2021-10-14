package com.company.Entities.User.View;

import javax.swing.*;

public class UserView extends JFrame {
    private JPanel panel;

    public UserView(String title){
        super(title);

        this.panel = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.panel);
        this.pack();
        this.setVisible(true);
    }
}
