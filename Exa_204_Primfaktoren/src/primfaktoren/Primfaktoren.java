/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primfaktoren;

import java.util.Scanner;

/**
 *
 * @author Denis Imeri
 */
public class Primfaktoren {
    private int count = 0;
    private int number;
    private int[] primefactors = new int[1000];

    public void setNumber(int number) {
        this.number = number;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Primfaktoren prim = new Primfaktoren();
        System.out.print("Zahl eingeben: ");
        int numb = scan.nextInt();
        prim.setNumber(numb);
        prim.compute();
        System.out.println(prim.toString());
    }

    public void compute() {
        boolean abfrage = false;
        for (int i = 2; i < (number / 2); i++) {
            if (number % i == 0) {
                if(isPrime(i)){
                    primefactors[count] = i;
                    count++;
                }
            }
        }
    }

    public boolean isPrime(int number) {
        for(int i = 2;i<(number/2);i++){
            if(number%i==0){
                return false;
            }
        }
        return true;
    }
    
    @Override
    public String toString() {
        String string = "Primfaktoren: ";
        for (int i = 0; i < count; i++) {
            string += primefactors[i] + ((i == count - 1) ? " " : ", ");
        }
        return string;
    }

}
