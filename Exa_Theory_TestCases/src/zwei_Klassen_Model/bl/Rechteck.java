/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zwei_Klassen_Model.bl;

/**
 *
 * @author Denis Imeri
 */
public class Rechteck {
    private int length;
    private int width;
    private int area;

    public Rechteck(int length, int width) {
        this.length = length;
        this.width = width;
    }
    
    public void calculate(){
        area = length * width;
    }

    public int getArea() {
        return area;
    }
    
}
