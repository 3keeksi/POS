/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import beans.Customer;
import java.util.Comparator;

/**
 *
 * @author crether
 */
public class SortByAge implements Comparator<Customer>{

    @Override
    public int compare(Customer t, Customer t1) {
        return t.getAge() - t1.getAge();
    }
    
}
