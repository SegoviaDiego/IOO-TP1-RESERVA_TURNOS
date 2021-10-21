package com.company;

import com.company.Entities.User.View.LoginView;
import com.company.Entities.User.View.UserView;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    public MainView() {
        setTitle("Reserva de turnos");

        LoginView loginView = new LoginView();

        setContentPane(loginView.$$$getRootComponent$$$());

        // Get default screen size
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int defaultScreenWidth = gd.getDisplayMode().getWidth();
        int defaultScreenHeight = gd.getDisplayMode().getHeight();

        setSize((int) (defaultScreenWidth * 0.6), (int) (defaultScreenHeight * 0.8));

        // Center jframe
        int x = (defaultScreenWidth - getWidth()) / 2;
        int y = (defaultScreenHeight - getHeight()) / 2;

        setLocation(x, y);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
