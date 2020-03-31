/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.util.Scanner;

/**
 *
 * @author Denis Imeri
 */
public class NumberAnalysis {
    private int number = 0;
    private boolean prime = false;
    private int digitSum = 0;
    
    public NumberAnalysis(int number){
        this.number = number;
    }
    
    @Override
    public String toString(){
        return String.format("number: %d --> %b -%d", number, prime, digitSum);
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter number: ");
        int num = scan.nextInt();
        NumberAnalysis na = new NumberAnalysis(num);
        System.out.println(na.toString());
    }
}
