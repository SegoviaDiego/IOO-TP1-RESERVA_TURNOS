package com.company.UI;

import javax.swing.*;
import java.awt.*;

public class ViewManager {
    private JFrame mainPanel;
    private LoginView loginView;

    public void init() {
        this.mainPanel = new JFrame();

        this.setTitle();

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int defaultScreenWidth = gd.getDisplayMode().getWidth();
        int defaultScreenHeight = gd.getDisplayMode().getHeight();

        this.fitToScreen(defaultScreenWidth, defaultScreenHeight);

        this.centerToScreen(defaultScreenWidth, defaultScreenHeight);

        this.loginView = new LoginView(this);
        this.loginView.init();
    }

    // Navigation methods

    public void goToLoginView() {
        this.mainPanel.getContentPane().removeAll();
        this.mainPanel.getContentPane().add(this.loginView.getView());
        this.mainPanel.getContentPane().validate();
        this.mainPanel.getContentPane().repaint();
    }

    public void goToMainMenuView() {
        this.mainPanel.getContentPane().removeAll();
        // @TODO: Replace with Main Menu
        this.mainPanel.getContentPane().add(this.loginView.getView());
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