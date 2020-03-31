/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package commandlineparams;

/**
 *
 * @author Denis Imeri
 */
public class CommandLineParams {
    
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}
