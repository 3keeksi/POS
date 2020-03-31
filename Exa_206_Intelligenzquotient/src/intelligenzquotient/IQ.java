/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intelligenzquotient;

import java.util.Scanner;

/**
 *
 * @author Denis Imeri
 */
public class IQ {
    private int[] values;
    private double average;
    private int modus;
    private int aboveAvg;
    private int belowAvg;
    private int upperBound;
    private int lowerBound;
    private int valuesInInterval;

    public int getUpperBound() {
        return upperBound;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getValuesInInterval() {
        return valuesInInterval;
    }
    
    
    
    public IQ(String zahlen) {
        int count = 0;
        Scanner s = new Scanner(zahlen).useDelimiter(";"); //oder String[] tokens = zahlen.split(";");
        while (s.hasNextInt()) {
            count++;
        }
        Scanner x = new Scanner(zahlen).useDelimiter(";");
        values = new int[count]; //und dann new int[tokens.length]
        for (int i = 0; i < values.length; i++) { //values.length usw.
            values[i] = x.nextInt();
        }
    }

    public void compute() {
        for (int value : values) {
            average += value;
        }
        average/=values.length;
        
        int i = 0;
        while(i<values.length){
            if(values[i]<average){
                belowAvg++;
            }
            else{
                aboveAvg++;
            }
            i++;
        }
        
        int temp;
        int temp2 = 0;
        int index = 0;
        for (i = 0; i < values.length; i++) {
            temp = 0;
            for (int j = i; j < values.length; j++) { // 
                if(values[i] == values[j]){
                    temp++;
                }
            }
            if(temp2<temp){
                index = i;
                temp2 = temp;
            }
        }
        modus = values[index];
    }

    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }

    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    public void computeInterval() {
        for (int i = 0; i < values.length; i++) {
            if(lowerBound<=values[i] && values[i]<=upperBound){
                valuesInInterval++;
            }
        }
    }

    public void output() {
        String string = "IQ-Werte: ";
        for (int i = 0; i < values.length; i++) {
            string += values[i] + ((i == values.length - 1) ? " " : ", ");
        }
        System.out.println(string);
        System.out.format("Arithmetisches Mittel: %f\n", average);
        System.out.format("Der häufigste Wert (=Modus): %d\n", modus);
    }
}
