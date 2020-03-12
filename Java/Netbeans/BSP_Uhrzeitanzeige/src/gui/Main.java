/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Dimension;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author crether
 */
public class Main extends javax.swing.JFrame {

    private Thread localThread;
    public TimePanel[] panels = new TimePanel[3];
    private Thread[] threads = new Thread[3];

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        
        panels[0] = new TimePanel();
        panels[0].init(ZoneId.systemDefault());
        localPanel.add(panels[0]);
        
        panels[1] = new TimePanel();
        pa2.add(panels[1]);
        panels[2] = new TimePanel();
        pa3.add(panels[2]);
        
        List<String> zones = new ArrayList<>();
        zones.add("none");
        zones.addAll(ZoneId.getAvailableZoneIds().stream().sorted().collect(Collectors.toList()));
        String[] strings = zones.toArray(new String[0]);
        cb2.setModel(new DefaultComboBoxModel<>(strings));
        cb3.setModel(new DefaultComboBoxModel<>(strings));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        localPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pa2 = new javax.swing.JPanel();
        cb2 = new javax.swing.JComboBox<>();
        pa3 = new javax.swing.JPanel();
        cb3 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                onResized(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                onShown(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridLayout(3, 1));

        localPanel.setLayout(new javax.swing.BoxLayout(localPanel, javax.swing.BoxLayout.X_AXIS));

        jLabel1.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        jLabel1.setText("Lokale Zeit");
        jLabel1.setMaximumSize(new java.awt.Dimension(200, 26));
        jLabel1.setMinimumSize(new java.awt.Dimension(200, 26));
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 26));
        localPanel.add(jLabel1);

        getContentPane().add(localPanel);

        pa2.setLayout(new javax.swing.BoxLayout(pa2, javax.swing.BoxLayout.X_AXIS));

        cb2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb2.setMaximumSize(new java.awt.Dimension(200, 32767));
        cb2.setMinimumSize(new java.awt.Dimension(200, 31));
        cb2.setOpaque(false);
        cb2.setPreferredSize(new java.awt.Dimension(100, 31));
        cb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onCb2Action(evt);
            }
        });
        pa2.add(cb2);

        getContentPane().add(pa2);

        pa3.setLayout(new javax.swing.BoxLayout(pa3, javax.swing.BoxLayout.X_AXIS));

        cb3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb3.setMaximumSize(new java.awt.Dimension(200, 32767));
        cb3.setMinimumSize(new java.awt.Dimension(200, 31));
        cb3.setOpaque(false);
        cb3.setPreferredSize(new java.awt.Dimension(100, 31));
        cb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onCb3Action(evt);
            }
        });
        pa3.add(cb3);

        getContentPane().add(pa3);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_onShown
        this.setPreferredSize(new Dimension(600, 300));
        this.setMinimumSize(new Dimension(600, 300));
        threads[0] = new Thread((Runnable) panels[0]);
        threads[0].start();
    }//GEN-LAST:event_onShown

    private void onResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_onResized
        if(panels[0] == null) return;
        panels[0].resize(this.getWidth() - 200);
        if(panels[1] == null) return;
        panels[1].resize(this.getWidth() - 200);
        if(panels[2] == null) return;
        panels[2].resize(this.getWidth() - 200);
    }//GEN-LAST:event_onResized

    private void onCb2Action(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onCb2Action
        String zone = (String) cb2.getSelectedItem();
        ZoneId z;
        if(zone.equalsIgnoreCase("none")) z = null;
        else z = ZoneId.of((String) cb2.getSelectedItem());
        panels[1].init(z);
        threads[1] = new Thread((Runnable) panels[1]);
        threads[1].start();
    }//GEN-LAST:event_onCb2Action

    private void onCb3Action(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onCb3Action
        String zone = (String) cb3.getSelectedItem();
        ZoneId z;
        if(zone.equalsIgnoreCase("none")) z = null;
        else z = ZoneId.of((String) cb3.getSelectedItem());
        panels[2].init(z);
        threads[2] = new Thread((Runnable) panels[2]);
        threads[2].start();
    }//GEN-LAST:event_onCb3Action

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cb2;
    private javax.swing.JComboBox<String> cb3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel localPanel;
    private javax.swing.JPanel pa2;
    private javax.swing.JPanel pa3;
    // End of variables declaration//GEN-END:variables
}