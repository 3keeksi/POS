/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package strings;

/**
 *
 * @author Denis Imeri
 */
public class StringTester {
    public static void main(String[] args) {
        //            0123456789 123456789 123456789 123456789
//        String str = "Nach.Weihnachten.esse.ich.gerne.Pizza.!!";
        
//        for (char character : str.toCharArray()){
//            System.out.println(character);
//        }
//        System.out.println(String.format("%s", str));
//        System.out.println(str.charAt(5));
//        System.out.println("");
//        
//        for (int i = 0; i < str.length(); i++) {
//            System.out.println(str.charAt(i));
//        }
//        String[] tokens = str.split(" "); //geht
//        for (String token : tokens) {
//            System.out.println(token);
//        }
//        
//        //Metacharcters: . + * ?
//        String[] tokens2 = str.split("."); //geht nicht, weil Metacharakter
//        for (String string : tokens2) {
//            System.out.println(string);
//        }
//        
//        String[] tokens3 = str.split("\\."); //geht
        
//        str.replace('.', ' '); //.replace gibt String zurück
//        String str2 = str.replace('.', ' ')&; //String ist immutable und deswegen bekommt man immer ein neues Objekt
//        String str3 = str.replaceFirst("\\.", " "); //erstes ist regex
//        String str4 = str.replace("ach", "Vor");
//        System.out.println(str);
//        System.out.println(str2);

//        System.out.println(str.contains("esse"));
//        
//        str = str.substring(5, 16); // endIndex muss +1 sein
//        System.out.println(str);

        String name = "ALABA";
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        
        System.out.println(name);
    }
}
