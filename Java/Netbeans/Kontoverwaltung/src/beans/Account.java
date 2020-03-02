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

    public boolean transaction(double amount) {
        if (amount < 0 && balance - amount < 0) {
            return false;
        } else if (amount > 0) {
            balance+=amount;
        } else {
            balance-=amount;
        }
        setText();
        return true;
    }

    public void setBalance(double balance) {
        this.balance = balance;
        setText();
    }
    
    public void setText() {
        label.setText(String.format("%.02f", balance));
    }

    public double getBalance() {
        return balance;
    }
}
