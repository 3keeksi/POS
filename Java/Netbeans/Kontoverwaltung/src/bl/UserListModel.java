/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import beans.Account;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;

/**
 * @author crether
 */
public class UserListModel extends AbstractListModel<AccountUser>{
    
    private List<AccountUser> users = new ArrayList<>();
    private Account acc;
    private JTextArea pane;

    public UserListModel(Account acc, JTextArea pane) {
        this.acc = acc;
        this.pane = pane;
    }

    @Override
    public int getSize() {
        return users.size();
    }

    @Override
    public AccountUser getElementAt(int i) {
        return users.get(i);
    }
    
    public void add(AccountUser user){
        users.add(user);
        this.fireContentsChanged(users, 0, users.size());
    }
    
    public void addAll(List<AccountUser> users){
        users.addAll(users);
        this.fireContentsChanged(users, 0, users.size());
    }

    public List<AccountUser> getUsers(){
        return users;
    }
    
    public boolean exists(AccountUser user){
        return users.contains(user);
    }
}
