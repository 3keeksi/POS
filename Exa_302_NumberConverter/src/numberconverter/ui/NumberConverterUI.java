/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package numberconverter.ui;

import java.util.InputMismatchException;
import java.util.Scanner;
import numberconverter.bl.NumberConverterBL;

/**
 *
 * @author Denis Imeri
 */
public class NumberConverterUI {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("Wählen sie aus:\n"
                    + "  (1) Dezimal -> Hexadezimal\n"
                    + "  (2) Hexadezimal -> Dezimal\n"
                    + "  (3) Dezimal -> Binaer\n"
                    + "  (4) Binaer -> Dezimal\n"
                    + "  (5) Ende\n"
                    + "Auswahl: ");
            int choice;
            try{
                choice = scan.nextInt();
            } catch (InputMismatchException e){
                choice = 0;
            }
            if(choice==5){
                System.out.println("Beenden...");
                break;
            }
            int intNum = 0;
            String strNum;
            switch(choice){
                case 0:
                    System.out.println("Bitte eine Zahl als Auswahl eingeben!");
                    break;
                case 1:
                    System.out.print("Dezimalzahl: ");
                    try{
                        intNum = scan.nextInt();
                        System.out.println(NumberConverterBL.toHexString(intNum));
                    } catch (InputMismatchException e){
                        System.out.println("Sie müssen Zahlen eintippen!\n");
                    }
                    break;
                case 2:
                    System.out.print("Hexadezimalahl: ");
                    strNum = scan.next();
                    System.out.println(NumberConverterBL.parseHexString(strNum));
                    break;
                case 3:
                    System.out.print("Dezimalzahl: ");
                    try{
                        intNum = scan.nextInt();
                        System.out.println(NumberConverterBL.decimalToBinary(intNum));
                    } catch (InputMismatchException e){
                        System.out.println("Sie müssen Zahlen eintippen!\n");
                    }
                    break;
                case 4:
                    System.out.print("Binaerzahl: ");
                    try{
                        intNum = scan.nextInt();
                        System.out.println(NumberConverterBL.binaryToDecimal(intNum));
                    } catch (InputMismatchException e){
                        System.out.println("Sie müssen Zahlen eintippen!\n");
                    }
                    break;
                default: 
                    System.out.println("Falsche Eingabe");
                    break;
            }
            scan.next();
        }
    }
}
