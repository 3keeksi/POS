/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import bl.ConverterBL;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author Denis Imeri
 */
public class ConverterGUI extends javax.swing.JFrame {
    
    public static String romanChars = "MDCLXVI";
    
    /**
     * Creates new form NewJFrame
     */
    public ConverterGUI() {
        initComponents();
        setLocationRelativeTo(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbDec = new javax.swing.JLabel();
        tfDec = new javax.swing.JTextField();
        lbRom = new javax.swing.JLabel();
        tfRom = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(400, 120));
        setMinimumSize(new java.awt.Dimension(400, 120));
        setPreferredSize(new java.awt.Dimension(400, 120));
        getContentPane().setLayout(new java.awt.GridLayout(2, 2, 10, 0));

        lbDec.setFont(new java.awt.Font("DejaVu Sans Mono", 0, 18)); // NOI18N
        lbDec.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbDec.setText("Dezimal-Zahl");
        lbDec.setMaximumSize(new java.awt.Dimension(120, 60));
        lbDec.setMinimumSize(new java.awt.Dimension(120, 60));
        lbDec.setPreferredSize(new java.awt.Dimension(120, 60));
        getContentPane().add(lbDec);

        tfDec.setFont(new java.awt.Font("DejaVu Sans Mono", 0, 18)); // NOI18N
        tfDec.setMaximumSize(new java.awt.Dimension(220, 60));
        tfDec.setMinimumSize(new java.awt.Dimension(220, 60));
        tfDec.setPreferredSize(new java.awt.Dimension(220, 60));
        tfDec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                onDecimalToRoman(evt);
            }
        });
        getContentPane().add(tfDec);

        lbRom.setFont(new java.awt.Font("DejaVu Sans Mono", 0, 18)); // NOI18N
        lbRom.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbRom.setText("Römische-Zahl");
        lbRom.setMaximumSize(new java.awt.Dimension(120, 60));
        lbRom.setMinimumSize(new java.awt.Dimension(120, 60));
        lbRom.setPreferredSize(new java.awt.Dimension(120, 60));
        getContentPane().add(lbRom);

        tfRom.setFont(new java.awt.Font("DejaVu Sans Mono", 0, 18)); // NOI18N
        tfRom.setMaximumSize(new java.awt.Dimension(220, 60));
        tfRom.setMinimumSize(new java.awt.Dimension(220, 60));
        tfRom.setPreferredSize(new java.awt.Dimension(220, 60));
        tfRom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                onRomanToDecimal(evt);
            }
        });
        getContentPane().add(tfRom);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onDecimalToRoman(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_onDecimalToRoman
        String strDecimal = tfDec.getText().trim();
        int decimal = 0;
        tfRom.setText("");
        tfDec.setForeground(Color.black);
        if(!strDecimal.equals("")){
            String txt = "";
            try{
                decimal = Integer.valueOf(strDecimal);
                txt = ConverterBL.decimalToRoman(decimal);
            }catch(NumberFormatException e){
                tfDec.setForeground(Color.red);
            }
            tfRom.setText(txt);
        }
    }//GEN-LAST:event_onDecimalToRoman

    private void onRomanToDecimal(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_onRomanToDecimal
        tfDec.setText("");
        tfRom.setForeground(Color.black);
        String strRoman = tfRom.getText().trim();
        
        for (char c : strRoman.toCharArray()) {
            if(!romanChars.contains("" + c)){
                tfRom.setForeground(Color.red);
                return;
            }
        }
        
        if(!strRoman.equals("")){
            try {
                int intTxt = ConverterBL.romanToDecimal(strRoman);
                String txt = ""+intTxt;
                tfDec.setText(txt);
            }
            catch (NumberFormatException e){
                tfRom.setForeground(Color.red);
            }
            
        }
    }//GEN-LAST:event_onRomanToDecimal

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
            java.util.logging.Logger.getLogger(ConverterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConverterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConverterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConverterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConverterGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbDec;
    private javax.swing.JLabel lbRom;
    private javax.swing.JTextField tfDec;
    private javax.swing.JTextField tfRom;
    // End of variables declaration//GEN-END:variables
}
