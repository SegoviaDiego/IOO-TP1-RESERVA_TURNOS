package com.company.UI;

import com.company.Negocio.User;

import javax.swing.*;
import java.awt.*;

public class ViewManager {
    private JFrame mainPanel;

    private LoginView loginView;

    private MainMenu mainMenu;

    private CreateAppointment createaAppointmentView;

    private User user;

    public void init() {
        this.mainPanel = new JFrame();

        this.setTitle();

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int defaultScreenWidth = gd.getDisplayMode().getWidth();
        int defaultScreenHeight = gd.getDisplayMode().getHeight();

        this.fitToScreen(defaultScreenWidth, defaultScreenHeight);

        this.centerToScreen(defaultScreenWidth, defaultScreenHeight);
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Navigation methods

    public void goToLoginView() {
        this.loginView = new LoginView(this);
        this.loginView.init();

        this.mainPanel.getContentPane().removeAll();
        this.mainPanel.getContentPane().add(this.loginView.getView());
        this.mainPanel.getContentPane().validate();
        this.mainPanel.getContentPane().repaint();
    }

    public void goToMainMenuView() {
        this.mainMenu = new MainMenu(this);
        this.mainMenu.init();

        this.mainPanel.getContentPane().removeAll();
        this.mainPanel.getContentPane().add(this.mainMenu.getView());
        this.mainPanel.getContentPane().validate();
        this.mainPanel.getContentPane().repaint();
    }

    public void goToCreateAppointmentView() {
        this.createaAppointmentView = new CreateAppointment(this);
        this.createaAppointmentView.init();

        this.createaAppointmentView.setUser(user);

        this.mainPanel.getContentPane().removeAll();
        this.mainPanel.getContentPane().add(this.createaAppointmentView.getView());
        this.mainPanel.getContentPane().validate();
        this.mainPanel.getContentPane().repaint();
    }

    // Internal methods

    public void show() {
        this.mainPanel.setVisible(true);
    }

    private void setTitle() {
        this.setTitle("Reserva de turnos");
    }

    private void setTitle(String title) {
        this.mainPanel.setTitle(title);
    }

    private void fitToScreen(int defaultScreenWidth, int defaultScreenHeight) {
        this.mainPanel.setSize((int) (defaultScreenWidth * 0.6), (int) (defaultScreenHeight * 0.8));
    }

    private void centerToScreen(int defaultScreenWidth, int defaultScreenHeight) {
        int x = (defaultScreenWidth - this.mainPanel.getWidth()) / 2;
        int y = (defaultScreenHeight - this.mainPanel.getHeight()) / 2;

        this.mainPanel.setLocation(x, y);

        this.mainPanel.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
