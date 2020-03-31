/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birthdate;

import java.util.Scanner;

/**
 *
 * @author Denis Imeri
 */
public class Birthdate {

    private int day = 0;
    private int month = 0;
    private int year = 0;
    private int dayOfYear = 0;
    private String monthname;

    public static void main(String[] args) {
        Birthdate birthdate = new Birthdate();
        birthdate.input();
        birthdate.compute();
        birthdate.output();
    }

    public void input() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your birthday:");
        System.out.print("Day: ");
        day = scan.nextInt();
        while(day<1 || 31<day){
            System.out.print("Enter the day again: ");
            day = scan.nextInt();
        }
        System.out.print("Month: ");
        month = scan.nextInt();
        while(month<1 || 12<month){
            System.out.print("Enter the month again: ");
            month = scan.nextInt();
        }
        System.out.print("Year: ");
        year = scan.nextInt();
    }

    public void compute() {
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if(year%4==0 && year%100!=0){
            days[1]=days[1]+1;
        }
        for(int i=0;i<month-1;i++){
            dayOfYear+=days[i];
        }
        dayOfYear+=day;
        String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
        monthname = months[month-1];
    }

    public void output() {
        System.out.format("Your birthday is: %2d. %s %4d.\n", day, monthname, year);
        System.out.format("The day of the year is: %d\n", dayOfYear);
    }
}
