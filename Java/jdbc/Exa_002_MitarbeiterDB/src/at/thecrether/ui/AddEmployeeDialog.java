/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.thecrether.ui;

import at.thecrether.beans.Employee;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 *
 * @author crether
 */
public class AddEmployeeDialog extends JDialog implements ActionListener {

    private final JPanel panel;
    private final GridBagConstraints gbc;

    private Employee emp;
    private final JTextField tfSurname;
    private final JTextField tfFirstname;
    private final JFormattedTextField tfGehalt;
    private DatePicker datePicker;
    private final JFormattedTextField tfAbtNr;
    private final JComboBox<String> cbGeschlecht;

    private final JButton btAdd;
    private final JButton btCancel;

    private int currentY = 0;
    private final int persNr;

    public AddEmployeeDialog(Frame parent, int persNr) {
        super(parent, "Insert a Employee", true);
        this.persNr = persNr;
        setMinimumSize(new Dimension(300, 350));
        panel = new JPanel();
        panel.setMinimumSize(new Dimension(300, 350));
        panel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = currentY++;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        panel.add(new JLabel("Add an Employee!", SwingConstants.CENTER), gbc);

        // Surname box
        tfSurname = new JTextField(40);
        addInput(new JLabel("Surname:"), tfSurname);

        // firstname box
        tfFirstname = new JTextField(40);
        addInput(new JLabel("Firstname:"), tfFirstname);

        // birthdate
        addDatePicker();

        // salary (gehalt)
        tfGehalt = new JFormattedTextField(NumberFormat.getCurrencyInstance(
                Locale.GERMANY));
        tfGehalt.setValue(1);
        tfGehalt.setColumns(10);
        addInput(new JLabel("Salary:"), tfGehalt);

        // abteilungs nummer (dep no)
        tfAbtNr = new JFormattedTextField(NumberFormat.getIntegerInstance());
        tfAbtNr.setColumns(2);
        tfAbtNr.setValue(1);
        addInput(new JLabel("Department Number:"), tfAbtNr);

        String[] options = {"Female", "Male"};
        cbGeschlecht = new JComboBox<>(options);
        addInput(new JLabel("Gender"), cbGeschlecht);

        // real fancy spacing
        JLabel spacer = new JLabel(" ");
        gbc.gridx = 0;
        gbc.gridy = currentY++;
        panel.add(spacer, gbc);

        // button to cancel the dialog
        btCancel = new JButton("Cancel");
        btCancel.addActionListener(this);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = ++currentY;
        panel.add(btCancel, gbc);

        // button to confirm the adding
        btAdd = new JButton("Add Employee");
        btAdd.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = currentY;
        panel.add(btAdd, gbc);

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(parent);
        System.out.println(datePicker);
    }

    private void addInput(JLabel tf, Component input) {
        // add the textfield
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = currentY;
        gbc.weightx = 0;
        panel.add(tf, gbc);

        // add the input field (datepicker etc)
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = currentY;
        gbc.weightx = 1;
        panel.add(input, gbc);
        currentY++;
    }

    private void addDatePicker() {
        JFXPanel fx = new JFXPanel();
        // add the input field (datepicker etc)
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = currentY;
        gbc.weightx = 2;
        gbc.insets = new java.awt.Insets(0, 10, 0, 0);
        fx.setMinimumSize(new Dimension(0, 40));
        fx.setOpaque(true);
        Color color = panel.getBackground();
        fx.setBackground(color);

        panel.add(fx, gbc);

        Platform.runLater(() -> {
            // this is just weird a lot of times
            fx.setScene(createDatePicker());
            setMinimumSize(new Dimension(300, getHeight()+50));
            pack();
        });

        currentY++;
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
    }

    private Scene createDatePicker() {
        GridPane grid = new GridPane();

        Label lbDate = new Label("Birthdate:");
        datePicker = new DatePicker(LocalDate.now());
        grid.getColumnConstraints().add(new ColumnConstraints(155));
//        grid.getColumnConstraints().add(new ColumnConstraints(Double.MAX_VALUE));
        grid.add(lbDate, 0, 0);
        grid.add(datePicker, 1, 0);

        // Vbox and scene
        VBox vbox = new VBox(20);
//        vbox.setPadding(new javafx.geometry.Insets(15, 15, 15, 15));
        vbox.getChildren().addAll(grid);
        Color bg = panel.getBackground();
        javafx.scene.paint.Color color = javafx.scene.paint.Color.rgb(bg.
                getRed(), bg.getGreen(), bg.getBlue());
        vbox.setBackground(new Background(new BackgroundFill(color,
                CornerRadii.EMPTY, Insets.EMPTY)));
        vbox.setPrefHeight(30);

        Scene scene = new Scene(vbox);
        return scene;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == btAdd) {
            Object val = tfGehalt.getValue();
            double salary = 0;
            if (val instanceof Double)
                salary = (double) val;
            else
                salary = Double.valueOf(val + "");
            LocalDate date = datePicker.getValue();
            String geschlecht = ((String) cbGeschlecht.getSelectedItem()).
                    charAt(0) + "";
            int abtNr = Integer.valueOf(tfAbtNr.getValue().toString());
            if (abtNr <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Your department number is invalid!");
                return;
            }
            if (salary <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Your salary is invalid!");
                return;
            }
            if (tfSurname.getText().length() == 0 || tfFirstname.getText().
                    length() == 0) {
                JOptionPane.showMessageDialog(this,
                        "You have on firstname or lastname typed in!");
                return;
            }
            emp = new Employee(persNr, tfSurname.getText(), tfFirstname.
                    getText(), date,
                    BigDecimal.valueOf(salary), abtNr,
                    geschlecht);
        } else
            emp = null;
        if (emp != null && EmployeesGUI.db.employees.contains(emp)) {
            emp = null;
            JOptionPane.showMessageDialog(this, "This employee already exists!");
            return;
        }
        dispose();
    }

    public Employee run() {
        this.setVisible(true);
        return emp;
    }

}
