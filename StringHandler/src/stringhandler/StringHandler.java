/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stringhandler;

import java.util.Scanner;

/**
 *
 * @author Denis Imeri
 */
public class StringHandler {
    public static String catStr(String str1, String str){
           String copy = "";
        for(int i=0;i<str1.length();i++){
            copy+=str1.charAt(i);
        }
        
        for(int i=0;i<str.length();i++){
            copy+=str.charAt(i);
        }
        return copy;
    }
    
    public static String insertStr(String str1, String str2, int pos){
        
        String copy = "";
        for(int i=0;i<pos;i++){
            copy+=str1.charAt(i);
        }
        copy=catStr(copy, str2);
        for(int i=pos;i<str1.length();i++){
            copy+=str1.charAt(i);
        }
        return copy;
    }
    
       /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("String-1: ");
        String str = scan.nextLine();
        System.out.print("String-2: ");
        String str2 = scan.nextLine();
        String cat = StringHandler.catStr(str, str2);
        System.out.println("Verkettung: " + cat);
        
        System.out.println("Teil 2");
        System.out.print("String-1: ");
        str = scan.nextLine();
        System.out.print("String-2: ");
        str2 = scan.nextLine();
        System.out.print("Position: ");
        int pos = scan.nextInt();
        System.out.println("Ergebnis: " + StringHandler.insertStr(str, str2, pos));
       
        
    }
    
}
