/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymath.bl;

/**
 *
 * @author Denis Imeri
 */
public class MyMath {

    public final static double PI = 3.141592653589793;

    public static int abs(int a) {
        int abs = a < 0 ? -a : a;
        return abs;
    }

    public static int min(int a, int b, int c) {
        int min = a;
        if (b < min) {
            min = b;
        }
        if (c < min) {
            min = c;
        }
        return min;
    }

    public static int max(int a, int b, int c) {
        int max = a;
        if (b > max) {
            max = b;
        }
        if (c > max) {
            max = c;
        }
        return max;
    }

    public static double power(double basis, int exp) {
        double pow = 1;
        for (int i = 1; i <= abs(exp); i++) {
            pow *= basis;
        }
        if (exp < 0) {
            pow = ((double) 1 / pow);
        }
        return pow;
    }

    public static double round(double wert, int stellen) {
        int check = (int) (wert * power(10, stellen + 1));
        if (check % 10 >= 5) {
            check /= 10;
            check += 1;
        } else {
            check /= 10;
        }
        return (double) check / power(10, stellen);
    }

    public static long fakultaet(int zahl) {
        long erg = 1;
        for (int i = 2; i <= zahl; i++) {
            erg *= i;
        }
        return erg;
    }

    public static double sin(double x) {
        double erg = 0;
        long fact;
        double power;
        for (int i = 0; i <= 10; i++) {
            power = power(x, (2*i+1));
            fact = fakultaet(2*i+1);
            
            erg += power(-1, i) * (double)(power / fact);
        }
        return erg;
    }

    public static double cos(double x) {
        double erg = 0;
        long fact;
        double power;
        for (int i = 0; i <= 10; i++) {
            power = power(x, (2*i));
            fact = fakultaet(2*i);
            
            erg += power(-1, i)*(double) (power / fact);
        }
        return erg;
    }
}
 