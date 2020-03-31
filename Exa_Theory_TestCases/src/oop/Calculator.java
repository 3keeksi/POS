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
public class Calculator {

    private int value;

    public Calculator() {
    }

    public Calculator(int value) {
        this.value = value;
    }

    //Überladen: Übergabeparameter müssen sich irgendwie unterscheiden
    public int add(int num1, int num2) {
        return num1 + num2;
    }

    public float add(float num1, float num2) {
        return num1 + num2;
    }

    public String add(String num1, String num2) {
        return num1 + num2;
    }

    public int max(int num1, int num2) {
        System.out.println("2 Ü");
        return num1 > num2 ? num1 : num2;
    }
    public int max(int num1, int num2, int num3){
        if(num1 > num2 && num1 > num3){
            return num1;
        }
        else if(num2 > num1 && num2 > num3){
            return num2;
        }
        return num3;
    }

    public int max(int val, int... values) {
        System.out.println("VarArg-Parameter");
        int max = val;

        for (int w : values) {
            max = w > max ? w : max;
        }
        return max;
    }

    public static void main(String[] args) {
        Calculator c1 = new Calculator();
        Calculator c2 = new Calculator(123);
//        System.out.println(c1.add(1, 2));
//        System.out.println(c1.add(1.0f, 2.0f));
//        System.out.println(c1.add("1", "2"));
        System.out.println(c1.max(1, 2));
        System.out.println(c1.max(3, 123, 12, 312));
    }
}
