/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conprod;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author crether
 */
public class Producer implements Runnable {
    private Stack stack;
    private int cnt;
    public static final Random rand = new Random();
    
    public Producer(Stack stack, int cnt) {
        this.stack = stack;
        this.cnt = cnt;
    }

    @Override
    public void run() {
        System.out.println("Producer started");
        for (int i = 0; i < 10; i++) {
            synchronized(stack) {
                while(stack.isFull()) {
                    try {
                        System.out.println("Producer has to wait");
                        stack.wait();
                        System.out.println("Producer finished waiting");
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println("pushing");
                stack.push(rand.nextInt(100));
                stack.notify();
                System.out.println(stack);
            }   
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
