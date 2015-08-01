package de.weichand.mapserver.mapfile;

import de.weichand.mapserver.mapfile.ItemType.Item;

/**
 *
 * @author weich_ju
 */
public class MapFactory 
{

    public static Item buildItem(String name, String value) 
    {
        Item item = new ItemType.Item();
        item.setName(name);
        item.setValue(value);
        return item;
    }
    
}
