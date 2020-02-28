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
public class Consumer implements Runnable {
    private Stack stack;
    private int cnt;
    private static final Random rand = new Random();

    public Consumer(Stack stack, int cnt) {
        this.stack = stack;
        this.cnt = cnt;
    }

    @Override
    public void run() {
        System.out.println("Consumer started");
        for (int i = 0; i < 10; i++) {
            synchronized(stack) {
                while(stack.isEmpty()) {
                    System.out.println("Stack empty");
                    try {
                        System.out.println("Consumer has to wait");
                        stack.wait();
                        System.out.println("Consumer finished waiting");
                    } catch(InterruptedException e) {
                        
                    }
                }
                int value = stack.pop();
                System.out.println("Consumer takes value: " + value);
                System.out.println(stack);
                stack.notify();
            }
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
}
