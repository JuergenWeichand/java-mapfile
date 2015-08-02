package de.weichand.mapserver.mapfile;

import java.io.File;
import javax.xml.bind.JAXBException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static de.weichand.mapserver.mapfile.MapFactory.buildItem;
import static de.weichand.mapserver.mapfile.MapFactory.buildRgbColorType;
import static de.weichand.mapserver.mapfile.MapFactory.buildOutputFormat;
import javax.xml.transform.TransformerException;
        
/**
 *
 * @author weich_ju
 */
public class MapfileTest {
    
    public MapfileTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testMapfile() throws JAXBException, TransformerException 
    {
        Map map = new Map();
        map.setName("OGC:WMS");
        map.setVersion("7.0.0");
        map.getProjection().add("init=epsg:31468");
        map.getExtent().add(4264375d);
        map.getExtent().add(5216375d);
        map.getExtent().add(4670625d);
        map.getExtent().add(5622625d);
        
        // OutputFormats
        map.getOutputFormat().add(buildOutputFormat(MapFactory.Format.AGG_PNG));
        map.getOutputFormat().add(buildOutputFormat(MapFactory.Format.AGG_JPEG));
              
        // Web
        Web web = new Web();
        ItemType webMetadata = new ItemType();
        webMetadata.getItem().add(buildItem("wms_title", "Java XML-Mapfile Demoserver"));
        webMetadata.getItem().add(buildItem("wms_onlineresource", "http://geoserv.weichand.de/cgi-bin/test-xmlmapfile.cgi"));
        webMetadata.getItem().add(buildItem("wms_enable_request", "*"));
        webMetadata.getItem().add(buildItem("wms_srs", "EPSG:31468 EPSG:31467 EPSG:4326 EPSG:4258 EPSG:25832 EPSG:25833"));
        webMetadata.getItem().add(buildItem("wms_encoding", "UTF-8"));
        web.setMetadata(webMetadata);
        map.getWeb().add(web);
        
        // Layer
        Layer layer = new Layer();
        layer.setName("gemeinden");
        layer.setType("POLYGON");
        layer.setData("/home/wei/linux-geodatenserver-beispiele/vektordaten/verwaltungsgrenzen/gmd_ex.shp");
        layer.setStatus("ON");
        layer.getProjection().add("init=epsg:31468");
        
        // Layer Metadata
        ItemType itemType = new ItemType();
        itemType.getItem().add(buildItem("wms_title", "Gemeinden"));
        itemType.getItem().add(buildItem("wms_abstract", "Gemeinden in Bayern"));
        layer.setMetadata(itemType);
        
        // Layer Style
        Class clazz = new Class();
        clazz.setName("Polygon");
        clazz.setColor(buildRgbColorType(232, 232, 232));
        clazz.setOutlineColor(buildRgbColorType(32, 32, 32));        
        layer.getClazz().add(clazz);
        
        map.getLayer().add(layer);
        
        
        final File mapFile = new File("/tmp/javamapfile.xml");
        // Writing
        MapfileIO.writeXmlMapfile(map, mapFile);
        
        // Reading
        System.out.println(
                MapfileIO.readMapFromFile(mapFile).
                        getWeb().get(0).
                        getMetadata().
                        getItem().get(0).
                        getValue());
    }
}