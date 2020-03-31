/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author crether
 */
public class Customer {
    
    private int location_id;
    private String firstname;
    private String lastname;
    private char gender;
    private LocalDate dateOfBirth;
    private int age;

    public Customer(int location_id, String firstname, String lastname, char gender, LocalDate dateOfBirth, int age) {
        this.location_id = location_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
    }

    @Override
    public String toString() {
        LocalDate date = LocalDate.now();
        return String.format("%s, %s (%c) - %d years old", lastname, firstname, gender, age);
    }
    
    public int getId() {
        return location_id;
    }
    
    public int getAge() {
        return age;
    }

    public int getLocation_id() {
        return location_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public char getGender() {
        return gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
    public static int getAgeDifference(LocalDate date) {
        LocalDate now = LocalDate.now().plusDays(1);
        Period period = Period.between(date, now);
        
        return period.getYears();
    }
    
}
