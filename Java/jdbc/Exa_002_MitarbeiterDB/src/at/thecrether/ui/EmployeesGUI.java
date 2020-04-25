/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.thecrether.ui;

import at.thecrether.beans.Employee;
import at.thecrether.bl.EmployeeTableModel;
import at.thecrether.db.DBAccess;
import at.thecrether.sql.FileLoader;
import java.awt.Component;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/**
 *
 * @author crether
 */
public class EmployeesGUI extends javax.swing.JFrame {

    public static DBAccess db;
    private EmployeeTableModel tableModel;

    /**
     * Creates new form EmployeesGUI
     */
    public EmployeesGUI() {
        try {
            db = new DBAccess();
            tableModel = new EmployeeTableModel(db.employees);
        } catch (ClassNotFoundException ex) {
            Platform.runLater(() -> {
                JOptionPane.
                        showMessageDialog(this, "The database setup failed!");
            });
        }
        setLocationByPlatform(true);
        initComponents();
        popTable.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (tbEmployees.getSelectedRowCount() > 0)
                        return;
                    int rowAtPoint = tbEmployees.rowAtPoint(SwingUtilities.
                            convertPoint(popTable, new Point(0, 0),
                                    tbEmployees));
                    if (rowAtPoint > -1)
                        tbEmployees.setRowSelectionInterval(rowAtPoint,
                                rowAtPoint);
                });
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popTable = new javax.swing.JPopupMenu();
        miDelete = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        btAddEmp = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        btInsertData = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btAvg = new javax.swing.JButton();
        lbAvg = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lbFilter = new javax.swing.JLabel();
        tfFilter = new javax.swing.JTextField();
        btFilter = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbEmployees = new javax.swing.JTable();

        miDelete.setText("Delete Employee");
        miDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onDelete(evt);
            }
        });
        popTable.add(miDelete);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(650, 600));
        setPreferredSize(new java.awt.Dimension(650, 450));

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPanel1.setMinimumSize(new java.awt.Dimension(100, 0));
        jPanel1.setName(""); // NOI18N
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        btAddEmp.setText("Add Employee");
        btAddEmp.setAlignmentX(0.5F);
        btAddEmp.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btAddEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onAddEmp(evt);
            }
        });
        jPanel1.add(btAddEmp);
        jPanel1.add(jSeparator3);

        btInsertData.setText("(Re-)Insert test data");
        btInsertData.setAlignmentX(0.5F);
        btInsertData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onInsertData(evt);
            }
        });
        jPanel1.add(btInsertData);

        jButton1.setText("Insert CSV Data");
        jButton1.setAlignmentX(0.5F);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onInsertCSV(evt);
            }
        });
        jPanel1.add(jButton1);
        jPanel1.add(jSeparator5);

        jButton2.setText("Connect to the DB");
        jButton2.setAlignmentX(0.5F);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onConnect(evt);
            }
        });
        jPanel1.add(jButton2);

        jButton3.setText("Disconnect from the DB");
        jButton3.setAlignmentX(0.5F);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onDisconnect(evt);
            }
        });
        jPanel1.add(jButton3);

        jButton4.setText("Create the DB");
        jButton4.setAlignmentX(0.5F);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onCreateDB(evt);
            }
        });
        jPanel1.add(jButton4);

        jButton5.setText("Create the Table");
        jButton5.setAlignmentX(0.5F);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onCreateTable(evt);
            }
        });
        jPanel1.add(jButton5);
        jPanel1.add(jSeparator1);

        btAvg.setText("Get average salary");
        btAvg.setAlignmentX(0.5F);
        btAvg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onAvg(evt);
            }
        });
        jPanel1.add(btAvg);

        lbAvg.setText("Average: Not Fetched");
        lbAvg.setAlignmentX(0.5F);
        jPanel1.add(lbAvg);
        jPanel1.add(jSeparator2);

        lbFilter.setText("Filter for a department");
        lbFilter.setAlignmentX(0.5F);
        jPanel1.add(lbFilter);

        tfFilter.setText("0");
        tfFilter.setMaximumSize(new java.awt.Dimension(400, 100));
        jPanel1.add(tfFilter);

        btFilter.setText("Filter");
        btFilter.setAlignmentX(0.5F);
        btFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onFilter(evt);
            }
        });
        jPanel1.add(btFilter);

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        tbEmployees.setModel(tableModel);
        tbEmployees.setComponentPopupMenu(popTable);
        jScrollPane1.setViewportView(tbEmployees);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onFilter(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onFilter
        try {
            String text = tfFilter.getText();
            int val = Integer.parseInt(text);
            if (val == 0) {
                db.employees = db.getEmployees();
                this.tableModel.changeData(db.employees);
                return;
            }
            this.tableModel.changeData(db.getEmployeesFromDepartment(val));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "There was an error when retrieving the data");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Your department number is invalid!");
        }
    }//GEN-LAST:event_onFilter

    private void onAvg(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onAvg
        Object[] options = {"Female", "Male"};
        int selected = JOptionPane.showOptionDialog(this,
                "Select a Gender", "Gender selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                options, options[0]);
        try {
            double val = db.getAverageSalary(((String) options[selected]).
                    charAt(0));
            lbAvg.setText(String.format("Average: %.2f", val));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "There was an error when retrieving the data");
        }
    }//GEN-LAST:event_onAvg

    private void onDelete(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onDelete
        Arrays.stream(tbEmployees.getSelectedRows()).forEach(i -> {
            Object[] row = tableModel.getSelectedRow(i);
            try {
                db.removeEmployee(new Employee(row));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                        "There was an error when deleting a employee");
            }
        });

        onFilter(null);
    }//GEN-LAST:event_onDelete

    private void onInsertData(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onInsertData
        try {
            db.insertEmployees();
            this.tableModel.changeData(db.employees);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "There was an error when inserting the test data");
        }
    }//GEN-LAST:event_onInsertData

    private void onAddEmp(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onAddEmp
        AddEmployeeDialog aed = new AddEmployeeDialog(this, db.highestPersNr + 1);
        Employee res = aed.run();
        if (res == null)
            return;
        try {
            db.insertEmployee(res);
            db.employees = db.getEmployees();
            tableModel.changeData(db.employees);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "There was an error when inserting the employee");
            System.out.println(ex.toString());
        }
        aed.invalidate();
    }//GEN-LAST:event_onAddEmp

    private void onInsertCSV(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onInsertCSV
        try {
            List<Employee> toInsert = FileLoader.loadCSV();
            for (Employee employee : toInsert) {
                employee.setPers_nr(db.highestPersNr + 1);
                db.insertEmployee(employee);
            }
            db.employees = db.getEmployees();
            tableModel.changeData(db.employees);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "could not find the csv file!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "could not insert employees");
        }
    }//GEN-LAST:event_onInsertCSV

    private void onConnect(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onConnect
        try {
            db.connect();
            JOptionPane.showMessageDialog(this,
                    "connected to the mitarbeiter database");
        } catch (SQLException ex) {
            int out = JOptionPane.showConfirmDialog(this,
                    "The mitarbeiter database does not exist.\nDo you want to create it?");
            // create database if the response is yes
            if (out == 0)
                try {
                db.createDB();
            } catch (SQLException ex1) {
                JOptionPane.showMessageDialog(this,
                        "could not create the database!");
            }
        }
    }//GEN-LAST:event_onConnect

    private void onDisconnect(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onDisconnect
        try {
            if (db.disconnect())
                JOptionPane.showMessageDialog(this,
                        "successfully disconnected from the database");
            else
                JOptionPane.showMessageDialog(this,
                        "could not disconnect the database because there was no connection");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "there was an error when disconnecting from the database");
        }
    }//GEN-LAST:event_onDisconnect

    private void onCreateDB(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onCreateDB
        try {
            db.disconnect();
            if (db.createDB())
                JOptionPane.showMessageDialog(this,
                        "successfully created the database");
            else
                JOptionPane.showMessageDialog(this, "recreated the database!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "could not create the database!\n"
                    + "are you maybe still connected to the database in your browser or in the app?");
        }
    }//GEN-LAST:event_onCreateDB

    private void onCreateTable(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onCreateTable
        try {
            if (!db.createTable())
                JOptionPane.showMessageDialog(this,
                        "there is no connection to the database.\n"
                        + "Could not create the table");
            else
                JOptionPane.
                        showMessageDialog(this, "successfully created the table");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "there was an error when creating the table");
        }
    }//GEN-LAST:event_onCreateTable

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.
                    getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EmployeesGUI.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeesGUI.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeesGUI.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeesGUI.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new EmployeesGUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAddEmp;
    private javax.swing.JButton btAvg;
    private javax.swing.JButton btFilter;
    private javax.swing.JButton btInsertData;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lbAvg;
    private javax.swing.JLabel lbFilter;
    private javax.swing.JMenuItem miDelete;
    private javax.swing.JPopupMenu popTable;
    private javax.swing.JTable tbEmployees;
    private javax.swing.JTextField tfFilter;
    // End of variables declaration//GEN-END:variables
}
