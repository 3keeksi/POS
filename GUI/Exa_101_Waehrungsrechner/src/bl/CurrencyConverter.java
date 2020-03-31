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
public class CurrencyConverter {
    public static double euroToDollar(double euro){
        return euro*1.13;
    }
    
    public static double dollarToEuro(double dollar){
        return dollar*0.89;
    }
}
