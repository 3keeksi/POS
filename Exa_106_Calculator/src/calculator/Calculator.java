/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.util.Scanner;

/**
 *
 * @author Denis Imeri
 */
public class Calculator {

    private double num1;
    private double num2;
    private char operator;
    private double result;

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.input();
        calc.calculate();
        calc.output();
    }

    public void input() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Erste Zahl eingeben: ");
        num1 = scan.nextInt();
        System.out.print("Operator eingeben: ");
        operator = scan.next().charAt(0);
        while(operator != '+' && operator != '-' && operator != '/' && operator != '*'){
            System.out.print("Bitte einen dieser Operator eingeben(+|-|/|*): ");
            operator = scan.next().charAt(0);
        }
        System.out.print("Zweite Zahl eingeben: ");
        num2 = scan.nextInt();
    }

    public void calculate() {
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            case '*':
                result = num1 * num2;
                break;
        }
    }

    public void output() {
        switch (operator) {
            case '+':
                System.out.print("Addition: ");
                break;
            case '-':
                System.out.print("Subtraktion: ");
                break;
            case '/':
                System.out.print("Division: ");
                break;
            case '*':
                System.out.print("Multiplikation: ");
                break;
        }
        System.out.format("%.2f %c %.2f = %.2f\n", num1, operator, num2, result);
    }
}
