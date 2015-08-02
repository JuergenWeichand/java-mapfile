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
    
    public static RgbColorType buildRgbColorType(int red, int green, int blue) 
    {
        final RgbColorType rgbColorType = new RgbColorType();
        rgbColorType.setRed(red);
        rgbColorType.setGreen(green);
        rgbColorType.setBlue(blue);
        return rgbColorType;
    }
    
}
