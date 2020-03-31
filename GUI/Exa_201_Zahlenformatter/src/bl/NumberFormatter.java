/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import java.text.DecimalFormat;

/**
 *
 * @author Denis Imeri
 */
public class NumberFormatter {
    
    public static String format(double number, String format, char fillChar, boolean alignRight, boolean tSep) throws Exception {
        String str = "" + fillChar;
        String strNumber = "";
        String[] fills = format.split("\\.");
        int[] fillNumber = new int[fills.length];
        for (int i = 0; i < fills.length; i++) {
            fillNumber[i] = Integer.parseInt(fills[i]);
        }
        String[] parts = new String[2];
        String formatted;
        if (fillNumber.length > 1) {
            String formatPattern = ".";
            for (int i = 0; i < fillNumber[1]; i++) {
                formatPattern += "#";
            }
            
            formatted = customFormat(formatPattern, number);
            parts = formatted.split("\\,");
        } else {
            formatted = String.format("%f", number);
            parts = formatted.split("\\.");
        }
        
        System.out.println(parts[0]);

        if (tSep) {
            String temp = parts[0];
            strNumber = "";
            for (int i = temp.length() - 1, j = 1; i >= 0; i--, j++) {
                if (j % 4 == 0) {
                    strNumber = "." + strNumber;
                    i++;
                    continue;
                }
                strNumber = temp.charAt(i) + strNumber;
            }
            try {
                if(parts[1].length()<Integer.parseInt(fills[1])){
                    System.out.println(Integer.valueOf(fills[1]));
                    int len = Integer.parseInt(fills[1]) - parts[1].length();
                    for(int i = 0;i<len;i++){
                        parts[1]+="0";
                    }
                }
                strNumber += "," + parts[1];
            } catch (IndexOutOfBoundsException e) {
                throw new Exception("Du hast kein Format für die Dezimalstellen eingegeben");
            }

        } else {
            strNumber = number + "";
        }

        if (alignRight) {
            int rest = fillNumber[0] - strNumber.length() - 2;
            for (int i = 0; i < rest; i++) {
                str += fillChar;
            }
            str += strNumber;
        } else {
            str += strNumber;

            int rest = fillNumber[0] - str.length() - 1;
            for (int i = 0; i < rest; i++) {
                str += fillChar;
            }
        }
        str += fillChar;
        return str;
    }

    public static String getDigitLine(int length) {
        String str = "";
        for (int i = 1; i <= length; i++) {
            if (i % 10 == 0) {
                str += " ";
                continue;
            }
            str += (i % 10 + "");
        }
        str += "\n";
        return str;
    }

    public static String customFormat(String pattern, double value) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String formatted = myFormatter.format(value);
        return formatted;
    }
}
