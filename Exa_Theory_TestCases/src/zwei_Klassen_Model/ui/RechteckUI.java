/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zwei_Klassen_Model.ui;

import java.util.Scanner;
import zwei_Klassen_Model.bl.Rechteck;

/**
 *
 * @author Denis Imeri
 */
public class RechteckUI {
    public static void main(String[] args) {
        //Einlesen der Breite und Länge
        Scanner scan = new Scanner(System.in);
        System.out.print("Length: ");
        int l = scan.nextInt();
        System.out.print("Width: ");
        int w = scan.nextInt();
        
        Rechteck rect = new Rechteck(l, w);
        rect.calculate();
        
        //Ausgabe der Fläche
        System.out.format("Area: %d", rect.getArea());
    }
}
