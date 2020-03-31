/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calctrainer.ui;

import calctrainer.bl.TrainingsCalculation;
import java.util.Scanner;

/**
 *
 * @author Denis Imeri
 */
public class CalcTrainerUI {

    public static void main(String[] args) {
        CalcTrainerUI trainerUI = new CalcTrainerUI();
        trainerUI.performTrainingsUnit();
    }

    public void performTrainingsUnit() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter number of rounds: ");
        int rounds = scan.nextInt();
        System.out.print("Enter difficulty (1/2): ");
        Byte difficulty = scan.nextByte();
        TrainingsCalculation trainer = new TrainingsCalculation(difficulty);
        for (int i = 0; i < rounds; i++) {
            System.out.format("Round %d\n", i + 1);
            trainer.performTrainingsUnit();
        }
        System.out.format("you achieved %d of %d points\n", trainer.getResult(), rounds);
    }
}
