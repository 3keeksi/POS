/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import java.util.Random;

/**
 *
 * @author Denis Imeri
 */
public class MinMaxBL {

    private int[] feld;
    private int min = 100;
    private int max = -1;

    public MinMaxBL(int[] feld) {
        this.feld = feld;
    }

    public void berechneMinMax() {
        for (int value : feld) {
            if (value < min) {
                this.min = value;
            }
            if (value > max) {
                this.max = value;
            }
        }
    }

    public int[] getFeld() {
        return feld;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
