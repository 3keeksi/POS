/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setter_getter;

import java.util.Scanner;

/**
 *
 * @author Denis Imeri
 */
public class Student {
    private String firstname;
    private String lastname;

    public Student(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        String txt = String.format("%s %s", firstname, lastname);
        return txt;//To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Vorname: ");
        String first = scan.next();
        System.out.print("Nachname: ");
        String last = scan.next();
        
        Student s1 = new Student(first, last);
        System.out.println(s1.toString());
        
        s1.setFirstname("Timo");
        s1.setLastname("Donelly");
        
        System.out.println("Vorname: " + s1.getFirstname());
        System.out.println("Nachname: " + s1.getLastname());
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
