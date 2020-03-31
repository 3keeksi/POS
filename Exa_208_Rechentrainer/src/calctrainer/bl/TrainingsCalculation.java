/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calctrainer.bl;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Denis Imeri
 */
public class TrainingsCalculation {

    private int number1;
    private int number2;
    private char operator;
    private int result;
    private byte difficulty;

    public TrainingsCalculation(byte difficulty) {
        this.difficulty = difficulty;
    }

    public void performTrainingsUnit() {
        int resultOfOperation = 0;
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        if (difficulty == 1) {
            number1 = rand.nextInt(10) + 1;
            number2 = rand.nextInt(10) + 1;
        } else {
            number1 = rand.nextInt(100) + 1;
            number2 = rand.nextInt(100) + 1;
        }
        int temp = rand.nextInt(4);
        switch (temp) {
            case 0:
                operator = '+';
                resultOfOperation = number1 + number2;
                break;
            case 1:
                operator = '-';
                resultOfOperation = number1 - number2;
                break;
            case 2:
                operator = '*';
                resultOfOperation = number1 * number2;
                break;
            case 3:
                operator = '/';
                resultOfOperation = number1 / number2;
                break;
        }
        System.out.println(toString());
        System.out.print("Enter result: ");
        int input;
        try {
            input = scan.nextInt();
        }catch(InputMismatchException e){
            System.out.println("Try again and type in an integer!");
            System.out.print("Enter result: ");
        }
        scan.nextLine();
        input = scan.nextInt();
        if (input == resultOfOperation) {
            System.out.println("perfect!\n");
            result++;
        } else {
            System.out.format("The correct result is %d\n\n", resultOfOperation);
        }
    }

    @Override
    public String toString() {
        String txt = String.format("%d %c %d = ?", number1, operator, number2);
        return txt;
    }

    public int getResult() {
        return result;
    }
}
