/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Denis Imeri
 */
public class Car {
    private String brand;
    private String model;
    private int speed = 0;
    private int desired = 0;
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Car car = new Car();
        car.input();
        car.output();
        
        System.out.print("Desired velocity(km/h)?: ");
        car.desired = scan.nextInt();
        while(car.desired<1 || 130<car.desired){
            System.out.print("You have to enter a velocity between 1 and 130 km/h: ");
            car.desired = scan.nextInt();
        }
        for(int i=0;i<car.desired;i++){
            car.accelerate();
            car.output();
            try {
                TimeUnit.MILLISECONDS.sleep(300L);
            } catch (InterruptedException ex) {
                Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.print("On what velocity do you want to slow down?(km/h): ");
        car.desired = scan.nextInt();
        for(int i=car.speed;i>car.desired;i--){
            car.slowDown();
            car.output();
            try {
                TimeUnit.MILLISECONDS.sleep(100L);
            } catch (InterruptedException ex) {
                Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void input(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the brand of the car: ");
        brand = scan.next();
        System.out.print("Enter the model of the car: ");
        model = scan.next();
    }
    public void output(){
        System.out.format("%s %s runs with %d Km/h\n", brand, model, speed);
    }
    public void accelerate(){
        speed+=1;
    }
    public void slowDown(){
        speed-=1;
    }
}
