/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package numberconverter.bl;

import java.util.Scanner;

/**
 * @author Denis Imeri
 */
public class NumberConverterBL {
    private static final char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String toHexString(int number) {
        int div;
        int mod;
        String erg = "";
        do {
            div = number / 16;
            mod = number % 16;
            erg = digits[mod] + erg;
            number = div;
        } while (div > 0);
        return erg;
    }

    public static int parseHexString(String hexString) {
        hexString.toUpperCase();
        int erg = 0;
        char temp;
        int index = 0;
        int counter = 0;
        for (int i = hexString.length() - 1; i >= 0; i--) {
            temp = hexString.charAt(i);
            for (int j = 0; j < digits.length; j++) {
                if (digits[j] == temp) {
                    index = j;
                    break;
                }
            }
            erg += (index * (int) Math.pow(16, counter));
            counter++;
        }
        return erg;
    }

    public static int decimalToBinary(int number) {
        int div;
        int mod;
        int erg = 0;
        int counter = 0;
        do {
            div = number / 2;
            mod = number % 2;
            erg += (mod * Math.pow(10, counter));
            number = div;
            counter++;
        } while (div > 0);
        return erg;
    }

    public static int binaryToDecimal(int number) {
        int erg = 0;
        int mod;
        int counter = 0;
        do {
            mod = number % 10;
            if (mod == 1) {
                erg += Math.pow(2, counter);
            }
            number /= 10;
            counter++;
        } while (number > 0);
        return erg;
    }
}
