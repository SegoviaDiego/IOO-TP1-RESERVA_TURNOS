package com.company.UI;

import com.company.Negocio.User;
import com.company.UI.Appointment.Schedule.SelectDoctor;

import javax.swing.*;
import java.awt.*;

public class ViewManager {

    private User loggedUser;

    private JFrame mainPanel;
    private LoginView loginView;
    private MainMenuView mainMenu;
    private SelectDoctor selectDoctorView;

    private JFrame createAppointmentFrame;
    private CreateAppointment createAppointment;

    public void init() {
        this.mainPanel = new JFrame();
        this.createAppointmentFrame = new JFrame();
        //this.createAppointmentFrame.setExtendedState(Frame.MAXIMIZED_BOTH);

        this.setTitle();

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int defaultScreenWidth = gd.getDisplayMode().getWidth();
        int defaultScreenHeight = gd.getDisplayMode().getHeight();

        this.fitToScreen(defaultScreenWidth, defaultScreenHeight);

        this.centerToScreen(defaultScreenWidth, defaultScreenHeight);

        this.loginView = new LoginView(this);
        this.loginView.init();

        this.mainMenu = new MainMenuView(this);
        this.mainMenu.init();

        this.createAppointment= new CreateAppointment(this);
        this.createAppointment.init();

        this.selectDoctorView = new SelectDoctor(this);
        this.selectDoctorView.init();
    }

    // Navigation methods

    public void goToLoginView() {
        this.mainPanel.getContentPane().removeAll();
        this.mainPanel.getContentPane().add(this.loginView.getView());
        this.mainPanel.getContentPane().validate();
        this.mainPanel.getContentPane().repaint();
    }

    public void goToMainMenuView(User user) {
        this.loggedUser= user;
        this.mainPanel.getContentPane().removeAll();
        this.mainPanel.getContentPane().add(this.mainMenu.getView(this.loggedUser));
        this.mainPanel.getContentPane().validate();
        this.mainPanel.getContentPane().repaint();
    }

    public void goToCreateAppointmentView() {
        this.createAppointmentFrame.getContentPane().add(this.createAppointment.getView(this.loggedUser));
        this.createAppointmentFrame.pack();
        this.createAppointmentFrame.getContentPane().validate();
        this.createAppointmentFrame.getContentPane().repaint();
        this.createAppointmentFrame.setVisible(true);

    }

    public void goToSelectDoctorView() {
        this.mainPanel.getContentPane().removeAll();
        this.mainPanel.getContentPane().add(this.selectDoctorView.getView());
        this.mainPanel.getContentPane().validate();
        this.mainPanel.getContentPane().repaint();
    }

    public void hideForm(){
        this.createAppointmentFrame.setVisible(false);
    }

    // Internal methods
    public void setLoggedUser(User user){this.loggedUser= user;}

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
