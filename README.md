# Java Mapfile
Reading and writing UMN MapSever XML-Mapfiles with Java

[![Build Status](https://travis-ci.org/JuergenWeichand/java-mapfile.svg?branch=master)](https://travis-ci.org/JuergenWeichand/java-mapfile)

## Building from source

    $ git clone https://github.com/JuergenWeichand/java-mapfile.git
    $ cd java-mapfile
    $ mvn clean install
  

## Create a new Map instance
    
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
    
## Write Map instance to file

    final File mapFile = new File("/tmp/javamapfile.xml");
    MapfileIO.writeXmlMapfile(map, mapFile);

## Read Map instance from file

    final File mapFile = new File("/tmp/javamapfile.xml");
    MapfileIO.readMapFromFile(mapFile);
