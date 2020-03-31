/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datatypes;

import java.util.Random;

/**
 *
 * @author Denis Imeri
 */
public class DataTypes {

    private byte b1;                                //[-128;127]

    public static void main(String[] args) {
        DataTypes datatypes = new DataTypes();
        datatypes.integerTest();
        datatypes.doubleTest();
        datatypes.booleanTest();
        datatypes.charTest();
        datatypes.utilClasses();
    }

    public void integerTest() {
        System.out.println("Integer: ");
        b1 = 1;
        System.out.println("Value of b1: " + b1);
        short s1 = 32_767;                          //[-32768;32767]
        System.out.println("Value of s1: " + s1);
        int i1 = 1_000_000_000;
        System.out.println("Value of i1: " + i1);
        System.out.format("Value of i1: %,d\n", i1);
        int i2 = 0777;
        System.out.println("Value of i2: " + i2);
        int i3 = 0xffffff;
        System.out.println("Value of i3: " + i3);
        long l1 = 123_456_789_012L;                 //L is wichtig
        System.out.println("Value of l1: " + l1);
        System.out.format("Value of l1: %-,40d##\n", l1);

        l1 = b1 + s1 + i1;
        b1 = (byte) s1;
        System.out.println("Value of b1: " + b1);

        //Operatoren: +,-,*,/,%,++,--,+=,-=,*=,/=,....
        //Wrapper-Klasse:
        Integer i5 = Integer.parseInt("1234");
        int i4 = 123;
        String str = Integer.toBinaryString(i4);
        System.out.format("%d is binary: $s\n", i4, str);

    }

    public void booleanTest() {
        int i1 = 1;
        int i2 = 1;
        int i3 = 1;
        boolean boo1 = true;
        if (boo1 || i1++ == 1) {  //short cicuit evalution
            System.out.println("value of i1: " + i1);
        }
        if (boo1 | i1++ == 1) {  //keine short cicuit evalution gibs auch bei &
            System.out.println("value of i1: " + i1);
        }
        if (boo1 ^ i1++ == 1) {  //wenn eins richtig und das andere false
            System.out.println("value of i1: " + i1);
        }
    }
    
    public void charTest(){
        char c1 = 'A';
        char c2 = '\u00A5';
        System.out.println(c2);
        char c3 = 65;
        System.out.println(c3);
        char c4 = '\n';
        
        //            0123456789 1
        String str = "Guten Morgen";
        char c5 = str.charAt(6);
        System.out.println(c5 + str);
        System.out.println(c5+12);
        System.out.println((char)(c5+12));
    }

    public void doubleTest() {
        float f1 = 1.0213f;
        double d1 = 1.2;
        d1 = Double.parseDouble("1.23456");
        System.out.format("%f-%f\n", f1, d1);
    }
    
    public void utilClasses(){
        Random rand = new Random();
        int i1 = rand.nextInt(45); //0-44 oder rand.nextInt(OG-UG+1) UG
        float f1 = rand.nextFloat()*10+100;
        
        double pi = Math.PI;
        double x = Math.sqrt(2);
        System.out.println(x);
        x = Math.pow(2, 3);
        System.out.println(x);
        x = Math.pow(8, 1./3.); //Kubikwurzel
        System.out.println(x);
    }
}
