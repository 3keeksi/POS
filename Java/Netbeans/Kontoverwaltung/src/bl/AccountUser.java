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
import javax.swing.JTextArea;

/**
 *
 * @author crether
 */
public class AccountUser implements Runnable {

    private String name;
    private Account acc;
    private JTextArea pane;
    private static Random rand = new Random();
    private AtomicInteger waitCnt = new AtomicInteger(0);

    public AccountUser(String name, Account acc, JTextArea pane) {
        this.name = name;
        this.acc = acc;
        this.pane = pane;
    }

    @Override
    public void run() {
        int deadlockCount = 0;
        for(int i = 0; i < 10; i++){
            deadlockCount = 0;
            try {
                pane.append(name + " has started\n");
                int amount = rand.nextInt(41) + 10;
                
                amount = rand.nextBoolean() ? amount : amount * (-1);
                
                synchronized(acc){
                    pane.append("Trying to make transaction --> " + amount + "\n");
                    while((acc.getBalance() + amount) < 0 && deadlockCount < 3){
                        try {
                            pane.append(name + " is waiting now\n");
                            acc.wait(2000);
                            deadlockCount++;
                            pane.append(name + " has finished waiting\n");
                        } catch (InterruptedException ex) {}
                    }
                    
                    if(deadlockCount >= 3){
                        i--;
                        continue;
                    }
                    
                    acc.performTransaction(amount);
                    pane.append(name + " performed transaction: " + amount + "\n");
                    acc.notifyAll();
                }
                
                Thread.sleep(rand.nextInt(1000) + 1);
            } catch (InterruptedException ex) {}
        }
        pane.append(name + " finished");
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.name);
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

    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }
    
    
}
