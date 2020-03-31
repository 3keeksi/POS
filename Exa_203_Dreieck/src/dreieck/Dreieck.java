/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dreieck;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Denis Imeri
 */
public class Dreieck {

    private int sideA;
    private int sideB;
    private int sideC;
    private String type;

    public Dreieck() {
        Random rand = new Random();
        this.sideA = rand.nextInt(6) + 5;
        this.sideB = rand.nextInt(6) + 5;
        this.sideC = rand.nextInt(6) + 5;
    }

    public Dreieck(int sideA, int sideB, int sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    public static void main(String[] args) {
        System.out.println("Extras: parameterlose Konstruktor");
        Scanner scan = new Scanner(System.in);
        System.out.print("Eingabe Seite A: ");
        int a = scan.nextInt();
        System.out.print("Eingabe Seite B: ");
        int b = scan.nextInt();
        System.out.print("Eingabe Seite C: ");
        int c = scan.nextInt();
        Dreieck drei = new Dreieck(a, b, c);
        drei.sortSides();
        drei.determineTypes();
        System.out.println(drei.toString());
    }

    public void sortSides() {
        int temp;
        if (sideA > sideB) {
            temp = sideA;
            sideA = sideB;
            sideB = temp;
        }
        if (sideA > sideC) {
            temp = sideA;
            sideA = sideC;
            sideC = temp;
        }
        if (sideB > sideC) {
            temp = sideB;
            sideB = sideC;
            sideC = temp;
        }
    }

    public void determineTypes() {
        if (sideA + sideB < sideC) {
            type = "kein Dreieck";
        } else if (sideA == sideB && sideB == sideC) {
            type = "gleichseitig";
        } else if ((sideA != sideB && sideB == sideC) || (sideA == sideB && sideB != sideC) || (sideA == sideC && sideC != sideB)) {
            type = "gleichschenkelig";
        } else if (Math.pow(sideA, 2) + Math.pow(sideB, 2) == Math.pow(sideC, 2)) {
            type = "rechtwinkelig";
        } else if (sideA + sideB > sideC) {
            type = "allgemein";
        } else {
            type = "kein Dreieck";
        }
    }

    @Override
    public String toString() {
        return String.format("Dreieck(%d, %d, %d) --> %s", sideA, sideB, sideC, type);
    }
}
