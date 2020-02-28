package conprod;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author crether
 */
public class Launcher {
    public static void main(String[] args) {
        Stack stack = new Stack(5); 
        int cnt = 100;
        Producer producer = new Producer(stack, cnt);
        Consumer consumer = new Consumer(stack, cnt);
        
        Thread prodThread = new Thread(producer);
        Thread consThread = new Thread(consumer);
        
        prodThread.start();
        consThread.start();
    }
}
