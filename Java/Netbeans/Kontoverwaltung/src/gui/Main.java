/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import beans.Account;
import bl.AccountUser;
import bl.UserListModel;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author crether
 */
public class Main extends javax.swing.JFrame {
    private UserListModel model;
    private Account account;

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        account = new Account(lbAccount, 0.);
        model = new UserListModel(account, epLog);
        liUsers.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popUsers = new javax.swing.JPopupMenu();
        miAdd = new javax.swing.JMenuItem();
        miTest = new javax.swing.JMenuItem();
        popLog = new javax.swing.JPopupMenu();
        miCreate = new javax.swing.JMenuItem();
        lbAccount = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        liUsers = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        epLog = new javax.swing.JEditorPane();

        miAdd.setText("add user");
        miAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUser(evt);
            }
        });
        popUsers.add(miAdd);

        miTest.setText("perform account test");
        miTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testAccount(evt);
            }
        });
        popUsers.add(miTest);

        miCreate.setText("create account");
        miCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountCreate(evt);
            }
        });
        popLog.add(miCreate);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbAccount.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        lbAccount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbAccount.setText("0,00 Euro");
        lbAccount.setBorder(javax.swing.BorderFactory.createTitledBorder("Account"));
        getContentPane().add(lbAccount, java.awt.BorderLayout.PAGE_END);

        jPanel1.setLayout(new java.awt.BorderLayout());

        liUsers.setBorder(javax.swing.BorderFactory.createTitledBorder("User"));
        liUsers.setComponentPopupMenu(popUsers);
        liUsers.setMinimumSize(new java.awt.Dimension(100, 177));
        jScrollPane1.setViewportView(liUsers);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.LINE_START);

        epLog.setBorder(javax.swing.BorderFactory.createTitledBorder("Log output"));
        epLog.setComponentPopupMenu(popLog);
        jScrollPane2.setViewportView(epLog);

        jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addUser(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUser
        String name = JOptionPane.showInputDialog("user");
        AccountUser user = new AccountUser(name, account, epLog);
        model.addUser(user);
    }//GEN-LAST:event_addUser

    private void testAccount(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testAccount
        // TODO add your handling code here:
        List<AccountUser> selected = liUsers.getSelectedValuesList();
        for (AccountUser user : selected) {
            System.out.println("asjdhvshjsg");
            Thread thread = new Thread(user);
            thread.start();
        }
    }//GEN-LAST:event_testAccount

    private void accountCreate(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountCreate
        account.setBalance(50);
        epLog.setText("account created");
    }//GEN-LAST:event_accountCreate

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
    private javax.swing.JEditorPane epLog;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbAccount;
    private javax.swing.JList<AccountUser> liUsers;
    private javax.swing.JMenuItem miAdd;
    private javax.swing.JMenuItem miCreate;
    private javax.swing.JMenuItem miTest;
    private javax.swing.JPopupMenu popLog;
    private javax.swing.JPopupMenu popUsers;
    // End of variables declaration//GEN-END:variables
}
