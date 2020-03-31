/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymath.ui;

import java.util.Scanner;
import mymath.bl.MyMath;

/**
 *
 * @author Denis Imeri
 */
public class MathTesterUI {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Menüauswahl: \n"
                + "---------------\n"
                + " (1) Absolutbetrag\n"
                + " (2) Max/Min Werte\n"
                + " (3) PI\n"
                + " (4) Fakultaet\n"
                + " (5) Sinus und Cosinus\n"
                + " (6) Sinus und Cosinus von Eingabe\n"
                + "---------------\n"
                + "Auswahl: ");
        int choice = scan.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Welcher Absolutbetrag: ");
                int absVar = scan.nextInt();
                System.out.format("Absolutbetrag von %d: %d\n", absVar, MyMath.abs(absVar));
                System.out.println("");
                break;
            case 2:
                System.out.println("Welche Max und Min-Werte: ");
                int[] werte = new int[3];
                for (int j = 0; j < 3; j++) {
                    System.out.format("Max/Min-Wert %d: ", j + 1);
                    werte[j] = scan.nextInt();
                }
                System.out.format("Max von %d, %d, %d: %d\n", werte[0], werte[1], werte[2], MyMath.max(werte[0], werte[1], werte[2]));
                System.out.format("Min von %d, %d, %d: %d\n", werte[0], werte[1], werte[2], MyMath.min(werte[0], werte[1], werte[2]));
                break;
            case 3:
                System.out.println("PI: " + MyMath.PI);
                System.out.format("PI mit 6 Dezimalstellen: %.6f\n", MyMath.PI);
                System.out.println("");
                break;
            case 4:
                System.out.print("Welche Fakultaet willst du: ");
                int fakul = scan.nextInt();
                System.out.println("Fakultaet von " + fakul + ": " + MyMath.fakultaet(fakul));
                System.out.println("");
                break;
            case 5:
                System.out.println("Sinus von 0: " + MyMath.sin(0));
                System.out.println("Sinus von PI/4: " + MyMath.sin(MyMath.PI / 4));
                System.out.println("Sinus von PI/2: " + MyMath.sin(MyMath.PI / 2));
                System.out.println("Sinus von PI: " + MyMath.sin(MyMath.PI));
                System.out.println("Cosinus von 0: " + MyMath.cos(0));
                System.out.println("Cosinus von PI/4: " + MyMath.cos(MyMath.PI / 4));
                System.out.println("Cosinus von PI/2: " + MyMath.cos(MyMath.PI / 2));
                System.out.println("Cosinus von PI: " + MyMath.cos(MyMath.PI));
                System.out.println(Math.cos(MyMath.PI));
                System.out.println("");
                break;
            case 6:
                System.out.print("Sinus und Cosinus für?: ");
                int sin = scan.nextInt();
                System.out.println("Sinus: " + MyMath.sin(sin));
                System.out.println(Math.sin(sin));
                System.out.println("Cosinus: " + MyMath.cos(sin));
                System.out.println(Math.cos(sin));
                break;
        }
    }
}
