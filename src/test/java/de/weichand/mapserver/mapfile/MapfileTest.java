package de.weichand.mapserver.mapfile;

import java.io.File;
import javax.xml.bind.JAXBException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static de.weichand.mapserver.mapfile.MapFactory.buildItem;
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
        
        // Web
        Web web = new Web();
        ItemType webMetadata = new ItemType();
        webMetadata.getItem().add(buildItem("wms_title", "Java Mapfile Demoserver"));
        webMetadata.getItem().add(buildItem("wms_onlineresource", "http://geoserv.weichand.de/cgi-bin/mapserv?map=/home/wei/javamapfile.xml"));
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
