/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author crether
 */
public class Student {
    private int studentId;
    private int catalogNr;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Student() {
    }
    
    public Student(int catNr, String firstname, String lastname, LocalDate birthdate) {
        this.catalogNr = catNr;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
    }

    public Student(int studentId, int catNr, String firstname, String lastname, LocalDate birthdate) {
        this.studentId = studentId;
        this.catalogNr = catNr;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCatNr() {
        return catalogNr;
    }

    public void setCatNr(int catNr) {
        this.catalogNr = catNr;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return String.format("%02d %s %s %s", catalogNr, firstname, lastname, DTF.format(birthdate));
    }
}
