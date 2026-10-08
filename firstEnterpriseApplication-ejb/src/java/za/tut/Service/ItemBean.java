/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatefulEjbClass.java to edit this template
 */
package za.tut.Service;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;
import za.tut.Entity.Item;

/**
 *
 * @author CodeWithLufuno
 */
@Stateful
public class ItemBean implements ItemService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    private List<Item> items;
    
    @PostConstruct
    public void initData()
    {
        items = new ArrayList();
    }
    
    //method for Adding an item
    @Override
    public void add(Item item) {
        items.add(item);
    }

    //method for deleting an item
    @Override
    public void deleteItem(int id) {
        for (Item item : items)
        {
            if (item.getId() == id)
            {
                items.remove(item);
            }
        }
    }

    //method for getting all items
    @Override
    public List<Item> getAll() {
        return items;
    }
    
}
