/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

/**
 *
 * @author Denis Imeri
 */
public class ConverterBL {

    public static char[] romanChars = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
    public static int[] divNumbs = {1000, 500, 100, 50, 10, 5, 1};

    public static String decimalToRoman(int decimal) {
        String str = "";
        
        //durch jeden romanChar gehen
        for (int i = 0; i < romanChars.length; i++) {
            //wie oft der bestimmte "Char" reinpasst
            int anz = decimal / divNumbs[i];
            //wie oft des halt dazu tut den buchstaben
            for (int j = 0; j < anz; j++) {
                str += romanChars[i];
            }
            decimal %= divNumbs[i];
        }
        
        //subtraktionsregel
        for (int i = 2; i < romanChars.length; i+=2){
            String temp = "";
            for(int j = 0;j<4;j++) temp+=romanChars[i];
            
            if(!str.contains(temp)){
                continue;
            }
            
            String replace = String.format("%c%c", romanChars[i], romanChars[i-1]);
            str = str.replace(temp, replace);
        }
        
        return str;
    }

    public static int romanToDecimal(String roman) {
        int erg = 0;
        for(int i = 0;i<roman.length();i++){
            if(roman.length()>1 && !(roman.length()-1 == i)){
                int firstIndex = getIndex(romanChars, roman.charAt(i));
                int secondIndex = getIndex(romanChars, roman.charAt(i+1));
                
                if(firstIndex>secondIndex){
                    throw new NumberFormatException();
                }
            }
            
            for (int j = 0; j < romanChars.length; j++) {
                if (roman.charAt(i) == romanChars[j]) {
                    erg += divNumbs[j];
                    break;
                }
            }
        }
        
        return erg;
    }
    
    public static int getIndex(char[] arr, char c) {
        for(int i = 0;i<arr.length;i++){
            if(arr[i] == c){
                return i;
            }
        }
        return -1;
    }

}
