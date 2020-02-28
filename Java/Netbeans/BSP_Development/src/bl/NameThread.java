/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author crether
 */
public class NameThread implements Runnable {

    private StringBuilder sb = new StringBuilder(10_000_000);

    @Override
    public void run() {
        for (int i = 0; i < 100_000; i++) {
            synchronized (sb) {
                sb.append(Thread.currentThread().getName());
            }
        }
    }

    public StringBuilder getSb() {
        return sb;
    }

    public static void main(String[] args) {
        NameThread nt = new NameThread();

        Thread thread1 = new Thread(nt, "Fred");
        Thread thread2 = new Thread(nt, "Laurenzia");
        Thread thread3 = new Thread(nt, "Bart");

        thread1.start();
        thread2.start();
        thread3.start();

        long startTime = System.currentTimeMillis();
        
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException ex) {
        }
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("duration " + duration);
        StringBuilder sb = nt.getSb();
        System.out.println(sb.length());
//        System.out.println(sb.substring(0, 1000));
    }

}
