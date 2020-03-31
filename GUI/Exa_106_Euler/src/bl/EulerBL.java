/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import java.math.BigInteger;

/**
 *
 * @author Denis Imeri
 */
public class EulerBL {

    public static int problem1() {
        int sum = 0;
        for (int i = 3; i < 1000; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        }
        return sum;
    }

    public static int problem9() {
        for (int i = 1; i < 1000; i++) {
            for (int j = 1; j < 1000; j++) {
                for (int k = 1; k < 1000; k++) {
                    if ((Math.pow(i, 2) + Math.pow(j, 2)) == Math.pow(k, 2)) {
                        if (i + j + k == 1000) {
                            return i * j * k;
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static int problem20() {
        BigInteger sum = BigInteger.valueOf(1);

        for (int i = 2; i <= 100; i++) {
            sum = sum.multiply(BigInteger.valueOf(i));
        }

        int lala = 0;

        while (sum.compareTo(BigInteger.valueOf(0)) == 1) {
            lala += Integer.parseInt(sum.mod(BigInteger.valueOf(10)).toString());
            sum = sum.divide(BigInteger.valueOf(10));
        }

        return lala;
    }

    public static long problem24() {
        long zahl = 123456789;
        for(int i=0;i<1000000;){
            if(noDouble(zahl)==0){
                i++;
            }
            zahl++;
        }
        return zahl-1;
    }

    public static int problem52() {
        boolean bool = false;
        int start = 1;
        int result=0;
        while (!bool) {
            start *= 10;
            for (int i = start; i < start * 10 / 6; i++) {
                bool = true;
                for (int j = 2; j <= 6; j++) {
                    if (!checkDigits(i, j * i)) {
                        bool = false;
                        break;
                    }
                }
                if (bool) {
                    result = i;
                    break;
                }
            }
        }
        return result;
    }
    
    public static long noDouble(long zahl){
        String str = zahl+"";
        if(str.length()==9){
            str = "0" + str;
        }
        for (int i = 0; i < 10; i++) {
            for (int j = i+1 ; j < 10; j++) {
                if(str.charAt(i)==str.charAt(j)){
                    return 1;
                }
            }
        }
        return 0;
    }

    public static boolean checkDigits(int int1, int int2) {
        int[] arr = new int[10];

        int temp = int1;
        while (temp > 0) {
            arr[temp % 10]++;
            temp /= 10;
        }

        temp = int2;
        while (temp > 0) {
            arr[temp % 10]--;
            temp /= 10;
        }

        for (int i = 0; i < 10; i++) {
            if (arr[i] != 0) {
                return false;
            }
        }
        return true;
    }

}
