/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.swing.JEditorPane;
import javax.swing.JLabel;

/**
 *
 * @author crether
 */
public class Account {

    private JLabel label;
    private double balance;

    public Account(JLabel label, double balance) {
        this.label = label;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
    
    public void performTransaction(int amount){
        if(balance + amount < 0){
            throw new RuntimeException("Balance cannot be negative");
        }
        
        balance += amount;
        label.setText(String.format("%.2f", balance));
    }
}
