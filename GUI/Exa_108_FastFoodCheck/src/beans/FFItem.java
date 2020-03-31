/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

/**
 *
 * @author Denis Imeri
 */
public class FFItem {
    
    private String name;
    private int energy;
    private int saturates;
    private int sugars;
    private int salt;

    public FFItem(String name, int energy, int saturates, int sugars, int salt) {
        this.name = name;
        this.energy = energy;
        this.saturates = saturates;
        this.sugars = sugars;
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getSaturates() {
        return saturates;
    }

    public void setSaturates(int saturates) {
        this.saturates = saturates;
    }

    public int getSugars() {
        return sugars;
    }

    public void setSugars(int sugars) {
        this.sugars = sugars;
    }

    public int getSalt() {
        return salt;
    }

    public void setSalt(int salt) {
        this.salt = salt;
    }
    
}
