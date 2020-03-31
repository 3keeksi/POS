/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package intelligenzquotient;

import java.util.Scanner;

/**
 *
 * @author Denis Imeri
 */
public class IQTester {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("IQs mit Trennzeichen getrennt eingeben: ");
        IQ iq = new IQ(scan.next());
        iq.compute();
        iq.output();
        
        System.out.print("Untergrenze eingeben: ");
        iq.setLowerBound(scan.nextInt());
        System.out.print("Oberrenze eingeben: ");
        iq.setUpperBound(scan.nextInt());
        
        iq.computeInterval();
        
        System.out.format("Im Bereich [%d ; %d] liegen %d IQ-Werte\n", iq.getLowerBound(), iq.getUpperBound(), iq.getValuesInInterval());
    }
}
