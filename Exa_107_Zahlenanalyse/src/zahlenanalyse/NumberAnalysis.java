/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zahlenanalyse;

import java.util.Scanner;

/**
 *
 * @author Denis Imeri
 */
public class NumberAnalysis {
    private int number;
    private boolean even;
    private boolean prime;
    private int digitSum = 0;
    private int temp;
    
    public static void main(String[] args) {
        NumberAnalysis numberA = new NumberAnalysis();
        numberA.input();
        numberA.calculate();
        numberA.output();
    }
    
    public void input(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Zahl eingeben: ");
        number = scan.nextInt();
    }
    public void calculate(){
        if(number%2==0){
            even = true;
        }
        else{
            even = false;
        }
        prime = isPrime();
        
        temp=number;
        while(temp > 0){
            digitSum+=temp%10;
            temp/=10;
        }
    }
    
    public boolean isPrime() {
        for(int i = 2;i<(number/2);i++){
            if(number%i==0){
                return false;
            }
        }
        return true;
    }
    
    public void output(){
        System.out.format("%d ist ", number);
        if(even){
            System.out.print("gerade, ");
        }
        else{
            System.out.print("ungerade, ");
        }
        if(prime){
            System.out.print("eine Primzahl, ");
        }
        else{
            System.out.print("keine Primzahl, ");
        }
        System.out.format("Ziffernsumme: %d\n", digitSum);
    }
}
