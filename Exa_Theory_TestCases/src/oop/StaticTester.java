/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

/**
 *
 * @author Denis Imeri
 */
public class StaticTester {

    private int value;
    private static int classValue = 2;
    public static final double PI; // final => kann nur einmal verändert werden

    static
    {
        System.out.println("Static: ");
        PI = 1.0 + 2.14159265;
    }
    
    {
        System.out.println("Instanceblock");
    }
    
    public StaticTester(int value) {
        this.value = value;
    }

    public static void setClassValue(int classValue) {
        StaticTester.classValue = classValue;
    }
    
    public static void print(){
        System.out.println(classValue);
    }

    @Override
    public String toString() {
        return String.format("Value: %d - classValue: %d", value, classValue);
    }

    public static void main(String[] args) {
        StaticTester st1 = new StaticTester(3);
        System.out.println(st1);
        StaticTester st2 = new StaticTester(123456);
        System.out.println(st2);
        StaticTester.setClassValue(-1);
        System.out.println(st1);
        StaticTester.print();
    }
}
