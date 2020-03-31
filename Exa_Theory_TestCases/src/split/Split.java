/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package split;

/**
 *
 * @author Denis Imeri
 */

public class Split {
    public static void main(String[] args) {
        String str = "David;Alaba;02.04.1994";
        String[] tokens = str.split(";");
        for (String token : tokens) {
            System.out.println(token);
        }
        String[] dateTokens = tokens[2].split("\\."); //Metacharaktere von Regular Expressions: . usw
        for (String dateToken : dateTokens) {
            System.out.println(dateToken);
        }
        
    }
}
