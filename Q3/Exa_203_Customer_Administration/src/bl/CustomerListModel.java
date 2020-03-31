/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import beans.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import bl.SortByAge;

/**
 *
 * @author crether
 */
public class CustomerListModel extends AbstractListModel {
    
    private List<Customer> customers = new ArrayList<>();
    private List<Customer> filteredCustomers = new ArrayList<>();
    int lastFilter = 0;
    String lastSort = "";

    public CustomerListModel(List<Customer> customers) {
        this.customers.addAll(customers);
    }

    @Override
    public int getSize() {
        return filteredCustomers.size();
    }

    @Override
    public Object getElementAt(int i) {
        return filteredCustomers.get(i);
    }
    
    public void add(Customer customer) {
        customers.add(customer);
        filter(lastFilter);
        sort(lastSort);
    }
    
    public void filter(int id) {
        filteredCustomers.clear();
        lastFilter = id;
        for (Customer customer : customers) {
            if(customer.getId() == id) {
                filteredCustomers.add(customer);
            }
        }
        this.fireContentsChanged(this, 0, filteredCustomers.size());
    }
    
    public void sort(String sort) {
        lastSort = sort;
        switch(sort.toLowerCase()){
            case "age":
                filteredCustomers.sort(new SortByAge());
                break;
            case "name":
                filteredCustomers.sort(new SortByLastname().thenComparing(new SortByFirstname()));
                break;
            default:
                return;
        }
        this.fireContentsChanged(this, 0, filteredCustomers.size());
    }
    
}
