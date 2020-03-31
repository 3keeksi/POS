/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibonacci;

import java.util.Scanner;

/**
 *
 * @author Denis Imeri
 */
public class Fibonacci {

    private int upperLimit;
    private int fiboSum = 0;

    public Fibonacci(int upperLimit) {
        this.upperLimit = upperLimit;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Obere Grenze eingeben: ");
        int upper = scan.nextInt();
        Fibonacci fibo = new Fibonacci(upper);
        fibo.computeSum();
        System.out.println(fibo.toString());
    }

    public void computeSum() {
        int f1 = 1;
        int f2 = 1;
        int f3 = 0;
        while (fiboSum <= upperLimit) {
            f3 = f1 + f2;
            if (f3 % 2 == 0) {
                fiboSum += f3;
            }
            f1 = f2;
            f2 = f3;
        }
    }

    @Override
    public String toString() {
        return String.format("Fibonacci-Summe bis %d: %d", upperLimit, fiboSum);
    }
}
