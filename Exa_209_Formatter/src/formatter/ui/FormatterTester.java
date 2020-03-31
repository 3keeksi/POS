/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package formatter.ui;

import formatter.Formatter;

/**
 *
 * @author Denis Imeri
 */
public class FormatterTester {
    public static void main(String[] args) {
        Formatter format = new Formatter();
        String var = "kaka";
        System.out.println(format.join("::", 1, 2, 3, 4, 5));
        System.out.println(format.join("|", var, "ist", "eine","tolle","sache"));
        System.out.println(format.join("//", 12.03,12.0,123.0,1112.120));
        System.out.println(format.join("::", 1, 2, 3, 4, 5));
        
        System.out.println(format.time("HH:mm:ss", 13, 25, 54));
        System.out.println(format.time("hh:mm", 13, 25, 54));
    }
}
