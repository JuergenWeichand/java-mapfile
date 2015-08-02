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
    
    public static OutputFormat buildOutputFormat(Format format) 
    {
        if (format.equals(Format.AGG_JPEG)) 
        {
            OutputFormat outputFormat = new OutputFormat();
            outputFormat.setName("jpeg");
            outputFormat.setDriver("AGG/JPEG");
            outputFormat.setMimeType("image/jpeg");
            outputFormat.setImageMode("RGB");
            outputFormat.setExtension("jpeg"); 
            return outputFormat;
        }
        if (format.equals(Format.AGG_PNG)) 
        {
            OutputFormat outputFormat = new OutputFormat();
            outputFormat.setName("png");
            outputFormat.setDriver("AGG/PNG");
            outputFormat.setMimeType("image/png");
            outputFormat.setImageMode("RGB");
            outputFormat.setExtension("png");            
            return outputFormat;
        }
        return null;
    }
    
    
    
    public enum Format
    {
        AGG_PNG,
        AGG_JPEG
    }
    
}
