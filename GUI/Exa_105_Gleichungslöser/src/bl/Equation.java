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
public class Equation {
    
    public static int[] parse(String equation){
        String[] temp = {"", "", ""}; 
        int[] splitted = new int[3];
        int index = 0, counter = 0;
        do{
            for(int j = counter;j<equation.length();j++){
                if((equation.charAt(j) == '+' || equation.charAt(j) == '-' ) && temp[index] != ""){
                    counter = j;
                    break;
                }
                temp[index] += equation.charAt(j);
            }
            splitted[index] = Integer.parseInt(temp[index]);
            index++;
        }while(index<3);
        return splitted;
    }
    
    public static double[] solve(int aCoeff, int bCoeff, int cCoeff){
        
    }
}
