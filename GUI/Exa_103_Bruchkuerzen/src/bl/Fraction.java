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
public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }
    
    public void cancel(){
        int a = numerator;
        int b = denominator;
        do{
            while(a>b){
                a -= b;
            }
            while(b>a){
                b -= a;
            }
        }while(a!=b);
        numerator/=a;
        denominator/=b;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }
}
