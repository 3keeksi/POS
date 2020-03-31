/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bl;

import beans.Animal;
import java.util.Comparator;

/**
 *
 * @author Denis Imeri
 */
public class SortBySpecies implements Comparator<Animal> {

    @Override
    public int compare(Animal o1, Animal o2) {
        return o1.getSpecies().compareTo(o2.getSpecies());
    }
    
}
