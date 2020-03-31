/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import beans.FFItem;
import io_access.IO_Access;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;

/**
 *
 * @author Belma
 */
public class ItemListModel extends AbstractListModel<FFItem> {
    
    private List<FFItem> items;

    public ItemListModel() {
        try {
            items = IO_Access.loadData();
            this.fireContentsChanged(null, 0, items.size());
        } catch (IOException ex) {
            Logger.getLogger(ItemListModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public FFItem getElementAt(int index) {
        return items.get(index);
    }
    
}
