/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package oop;

/**
 *
 * @author Denis Imeri
 */
public class ObjectCounter {
    private static int counter = 0;

    public ObjectCounter() {
        counter++;
    }
    
    public static void main(String[] args) {
        for(int i = 0; i<100;i++){
            new ObjectCounter();
        }
        System.out.println(ObjectCounter.counter);
    }
}
