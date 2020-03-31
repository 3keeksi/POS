/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package person;
import java.util.Scanner;

/**
 *
 * @author denis imeri
 */
public class Person {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); //create an object of class Scanner
        
        System.out.print("Please enter your firstname: ");
        String firstname = scan.next(); //scans the next String input from the console
        
        System.out.print("Please enter your age: ");
        int age = scan.nextInt(); // scans the next int input from the console
        
        System.out.format("Your firstname is %s and you are %d years old!\n", firstname, age);
    }
    
}
