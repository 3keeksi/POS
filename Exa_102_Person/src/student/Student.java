/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import java.util.Scanner;

/**
 *
 * @author Denis Imeri
 */
public class Student{
   private String firstname;
   private String lastname;
   private int age;
   
   public void input(){
       Scanner scan = new Scanner(System.in);
       System.out.print("Firstname: ");
       firstname = scan.next();
       System.out.print("Lastname: ");
       lastname = scan.next();
       System.out.print("Age: ");
       age = scan.nextInt();
   }
   public void output(){
       System.out.format("Your name is: %s %s\n"
               + "You are %d years old\n", firstname, lastname, age);
   }
   public static void main(String[] args) {
       Student student1 = new Student();
       student1.input();
       
       Student student2 = new Student();
       student2.input();
       
       student1.output();
       student2.output();
   }
}
