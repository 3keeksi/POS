/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bl;
import java.lang.NumberFormatException;


/**
 *
 * @author Denis Imeri
 */
public class DateTimeFormatter {
    public static boolean isIn(int start, int n, int end){
        if(start<=n && n<=end){
            return true;
        }
        return false;
    }
    
    public static String format(String formatStr, int ...values){
        String txt = "";
        if(formatStr == "DT" && values.length==5){
            txt = String.format("%02d.%02d.%04d - %02d:%02d", values[0], values[1], values[2], values[3], values[4]);
        }
        else if(formatStr == "T" && values.length==2){
            txt = String.format("%02d:%02d", values[0], values[1]);
        }
        else if(formatStr == "D" && values.length==3){
            txt = String.format("%02d.%02d.%04d", values[0], values[1], values[2]);
        }
        else{
            throw new NumberFormatException("Wrong parameters");
        }
        return txt;
    }
}
