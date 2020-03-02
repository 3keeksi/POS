/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import beans.Account;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;

/**
 *
 * @author crether
 */
public class AccountUser implements Runnable {

    private String name;
    private Account acc;
    private JEditorPane pane;
    private static Random rand = new Random();
    private AtomicInteger waitCnt = new AtomicInteger(0);

    public AccountUser(String name, Account acc, JEditorPane pane) {
        this.name = name;
        this.acc = acc;
        this.pane = pane;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void run() {
        int deadlockCount = 0;
        for (int i = 0; i < 10; i++) {
            deadlockCount = 0;
            System.out.println("reeee");
            int val = rand.nextInt(40) + 10;
            String makes = "deposits";
            if (rand.nextBoolean()) {
                val *= -1;
                makes = "withdraws";
            }
            synchronized (acc) {
                while ((acc.getBalance() + val) < 0 && deadlockCount < 3) {
                    try {
                        append("is waiting");
                        deadlockCount++;
                        acc.wait(2000);
                    } catch (InterruptedException ex) {
//                        Logger.getLogger(AccountUser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                if(deadlockCount >= 3){
                        i--;
                        continue;
                    }
                acc.transaction(val);
                makesTransaction(makes, val);
                acc.notifyAll();
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AccountUser other = (AccountUser) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    private void makesTransaction(String makes, double amount) {
        pane.setText(String.format("%s\n%s%s: %f", pane.getText(), name, makes, amount));
    }

    private void append(String str) {
        pane.setText(pane.getText() + "\n" + str);
    }

}
