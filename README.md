# Java Mapfile
Reading and writing UMN MapServer XML-Mapfiles with Java

[![Build Status](https://travis-ci.org/JuergenWeichand/java-mapfile.svg?branch=master)](https://travis-ci.org/JuergenWeichand/java-mapfile)

## Building from source

    $ git clone https://github.com/JuergenWeichand/java-mapfile.git
    $ cd java-mapfile
    $ mvn clean install
  

## Create a new Map instance
    
    Map map = new Map();
    map.setName("OGC:WMS");
    map.getProjection().add("init=epsg:31468");
    map.getExtent().add(4264375d);
    map.getExtent().add(5216375d);
    map.getExtent().add(4670625d);
    map.getExtent().add(5622625d);


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
        
    
## Write Map instance to file

    final File mapFile = new File("/tmp/javamapfile.xml");
    MapfileIO.writeXmlMapfile(map, mapFile);

## Read Map instance from file

    final File mapFile = new File("/tmp/javamapfile.xml");
    Map map = MapfileIO.readMapFromFile(mapFile);

## CGI-Wrapper for XML-Mapfile

    #!/bin/sh
    MAPSERV="/usr/lib/cgi-bin/mapserv"
    MS_XMLMAPFILE_XSLT="/path/to/mapfile.xsl" MS_MAPFILE="/path/to/javamapfile.xml" exec ${MAPSERV}