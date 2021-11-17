package com.company.UI;

import com.company.Negocio.Appointment;
import com.company.Negocio.Doctor;
import com.company.Negocio.User;
import com.company.Servicio.AppointmentService;
import com.company.Servicio.DoctorService;
import com.company.Servicio.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class CreateAppointment extends BasicView {
    private JPanel selectorPanel;
    private JLabel selectTimeLabel;
    private JLabel selectDoctorLabel;
    private JComboBox doctorComboBox;
    private JLabel selectDateLabel;
    private JComboBox appointmentCombo;
    private JButton saveAppointmentButton;
    private JButton cancelButton;
    private JComboBox dayCombo;
    private JLabel diaLabel;
    private JComboBox monthCombo;
    private JComboBox yearCombo;
    private JLabel mesLabel;
    private JLabel añoLabel;
    private JPanel mainPanel;

    private User user;

    public CreateAppointment(ViewManager viewManager) {
        super(viewManager);
    }

    @Override
    public void init() {
        this.$$$setupUI$$$();

        this.loadDoctorsComboBox();

        this.loadDatePickerComponent();

        refreshAvailableAppointmentList();

        this.saveAppointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppointmentService appointmentService = new AppointmentService();

                long userId = getUser().getId();

                long doctorId = getDoctor().getId();

                int year = getSelectedYear();
                int month = getSelectedMonth();
                int day = getSelectedDay();
                int startsAt = getSelectedAppointmentTime();

                if (isAppointmentAvailable(startsAt)) {

                    Appointment appointment = new Appointment(1, doctorId, userId, year, month, day, startsAt);

                    appointmentService.create(appointment);
                }

                goToMainMenuView();
            }
        });

        this.cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToMainMenuView();
            }
        });

        this.doctorComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshAvailableAppointmentList();
            }
        });

        this.yearCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshDayComboBox();
                refreshAvailableAppointmentList();
            }
        });

        this.monthCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshDayComboBox();
                refreshAvailableAppointmentList();
            }
        });

        this.dayCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshAvailableAppointmentList();
                refreshAvailableAppointmentList();
            }
        });
    }

    private void goToMainMenuView() {
        this.viewManager.goToMainMenuView();
        this.viewManager.hideForm();
    }

    private void loadDoctorsComboBox() {
        doctorComboBox.removeAllItems();
        UserService userService = new UserService();

        List<User> doctors = userService.getAllDoctors();

        if (!doctors.isEmpty())
            doctors.stream().forEach(doctor -> doctorComboBox.addItem(this.getDoctorComboBoxItem(doctor)));
    }

    private void loadDatePickerComponent() {
        // Setup Years
        this.yearCombo.removeAllItems();
        for (int i = 2021; i >= 2000; i--) {
            this.yearCombo.addItem(i);
        }

        // Setup Months
        this.monthCombo.removeAllItems();
        for (int i = 1; i <= 12; i++) {
            this.monthCombo.addItem(Month.of(i).getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        }

        // Setup Days
        this.refreshDayComboBox();
    }

    private void refreshDayComboBox() {
        int selectedYearValue = getSelectedYear();
        int selectedMonthId = this.getSelectedMonth();

        int lengthOfCurrentMonth = YearMonth.of(selectedYearValue, selectedMonthId).lengthOfMonth();

        if (lengthOfCurrentMonth != this.dayCombo.getItemCount()) {
            this.dayCombo.removeAllItems();

            // Setup Years
            for (int i = 1; i <= lengthOfCurrentMonth; i++) {
                this.dayCombo.addItem(i);
            }
        }
    }

    private int getSelectedYear() {
        return (int) this.yearCombo.getSelectedItem();
    }

    private int getSelectedMonth() {
        return ((int) this.monthCombo.getSelectedIndex()) + 1;
    }

    private int getSelectedDay() {
        return (int) this.dayCombo.getSelectedItem();
    }

    private int getSelectedAppointmentTime() {
        Doctor doctor = getDoctor();
        int shiftStartsAt = doctor.getStartsAt();

        return shiftStartsAt + this.appointmentCombo.getSelectedIndex();
    }

    private User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private Doctor getDoctor() {
        DoctorService doctorService = new DoctorService();

        List<Doctor> doctors = doctorService.findAll();

        int doctorIndex = this.doctorComboBox.getSelectedIndex();

        return doctors.get(doctorIndex);
    }

    private String getDoctorComboBoxItem(User doctor) {
        return doctor.getId() + " " + doctor.getFullName();
    }

    private void refreshAvailableAppointmentList() {
        this.appointmentCombo.removeAllItems();

        Doctor doctor = getDoctor();

        int shiftStartsAt = doctor.getStartsAt();
        int shiftEndsAt = doctor.getEndsAt();

        long shiftDuration = shiftEndsAt - shiftStartsAt;


        for (int i = shiftStartsAt; i < shiftStartsAt + shiftDuration; i++) {

            if (isAppointmentAvailable(i)) {
                this.appointmentCombo.addItem("Turno: de " + i + " a " + (i + 1));
            } else {
                this.appointmentCombo.addItem("Turno: de " + i + " a " + (i + 1) + "--- RESERVADO ---");
            }
        }
    }

    private boolean isAppointmentAvailable(int appointmentStartsAt) {
        Doctor doctor = getDoctor();
        AppointmentService appointmentService = new AppointmentService();

        List<Appointment> appointments = appointmentService.findAll();
        long doctorId = doctor.getId();

        Optional<Appointment> existingAppointment = appointments.stream()
                .filter(a -> a.getDoctorId() == doctorId)
                .filter(a -> a.getYear() == getSelectedYear() && a.getMonth() == getSelectedMonth() && a.getDay() == getSelectedDay())
                .filter(a -> a.getStartsAt() == appointmentStartsAt)
                .findFirst();

        return !existingAppointment.isPresent();
    }

    @Override
    public JComponent getView() {
        return this.$$$getRootComponent$$$();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(5, 5, 5, 5), -1, -1));
        selectorPanel = new JPanel();
        selectorPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        selectorPanel.setVisible(true);
        mainPanel.add(selectorPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        selectTimeLabel = new JLabel();
        selectTimeLabel.setText("Seleccione un turno");
        selectorPanel.add(selectTimeLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        selectDoctorLabel = new JLabel();
        selectDoctorLabel.setText("Seleccione un doctor");
        selectorPanel.add(selectDoctorLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        doctorComboBox = new JComboBox();
        selectorPanel.add(doctorComboBox, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        selectDateLabel = new JLabel();
        selectDateLabel.setText("Seleccione una fecha");
        selectorPanel.add(selectDateLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        appointmentCombo = new JComboBox();
        selectorPanel.add(appointmentCombo, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        saveAppointmentButton = new JButton();
        saveAppointmentButton.setText("Reservar turno");
        selectorPanel.add(saveAppointmentButton, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Cancelar");
        selectorPanel.add(cancelButton, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        selectorPanel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        dayCombo = new JComboBox();
        panel1.add(dayCombo, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        diaLabel = new JLabel();
        diaLabel.setText("Dia");
        panel1.add(diaLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        monthCombo = new JComboBox();
        panel1.add(monthCombo, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        yearCombo = new JComboBox();
        panel1.add(yearCombo, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mesLabel = new JLabel();
        mesLabel.setHorizontalAlignment(0);
        mesLabel.setHorizontalTextPosition(0);
        mesLabel.setText("Mes");
        panel1.add(mesLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        añoLabel = new JLabel();
        añoLabel.setText("Año");
        panel1.add(añoLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    /**
     * @noinspection ALL
     */

}
