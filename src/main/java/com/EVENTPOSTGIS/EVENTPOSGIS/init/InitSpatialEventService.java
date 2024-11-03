package com.EVENTPOSTGIS.EVENTPOSGIS.init;

import com.EVENTPOSTGIS.EVENTPOSGIS.Models.Entity.SpatialEvent;
import com.EVENTPOSTGIS.EVENTPOSGIS.Repository.SpatialEventRepository;
import org.locationtech.jts.geom.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class InitSpatialEventService implements ApplicationListener<ApplicationReadyEvent>
{

    @Autowired
    private SpatialEventRepository spatialEventRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        if(spatialEventRepository.count() > 1)
        {
            System.out.println("there are already an items");
          return;
        }

        SpatialEvent spatialEvent = new SpatialEvent();
         GeometryFactory geometryFactory = new GeometryFactory();

// Correction de la syntaxe pour créer un Point
        Point point = geometryFactory.createPoint(new Coordinate(4, 5));
        spatialEvent.setPoint(point);

// Création d'un MultiPoint
        Coordinate[] coordinates = new Coordinate[] { new Coordinate(39, 33), new Coordinate(46, 23) };
        MultiPoint multiPoint = geometryFactory.createMultiPointFromCoords(coordinates);

// Création de LineStrings
        Coordinate[] coordinates1 = new Coordinate[] { new Coordinate(39, 33), new Coordinate(46, 23) };
        LineString lineString1 = geometryFactory.createLineString(coordinates1);

        Coordinate[] coordinates2 = new Coordinate[] { new Coordinate(26, 38), new Coordinate(30, 37), new Coordinate(40.5, 39.785) };
        LineString lineString2 = geometryFactory.createLineString(coordinates2);

// Création d'un MultiLineString
        LineString[] lineStrings = new LineString[] { lineString1, lineString2 };
        MultiLineString multiLineString = geometryFactory.createMultiLineString(lineStrings);

// Création d'un Polygon
        Coordinate[] polygonCoordinates = new Coordinate[] {
                new Coordinate(39, 33),
                new Coordinate(46, 23),
                new Coordinate(40, 27),
                new Coordinate(39, 33) // Fermeture du polygone
        };

// Création d'un trou
        Coordinate[] holeCoordinates = new Coordinate[] {
                new Coordinate(40, 34),
                new Coordinate(45, 24),
                new Coordinate(41, 26),
                new Coordinate(40, 34) // Fermeture du trou
        };
        LinearRing hole = geometryFactory.createLinearRing(holeCoordinates);
        LinearRing shell = geometryFactory.createLinearRing(polygonCoordinates);
        Polygon polygonWithHole = geometryFactory.createPolygon(shell, new LinearRing[]{ hole });

// Création d'un MultiPolygon
        Polygon[] polygons = new Polygon[] { polygonWithHole };
        MultiPolygon multiPolygon = geometryFactory.createMultiPolygon(polygons);

// Création d'une GeometryCollection
        Geometry[] geometries = new Geometry[] { point, multiLineString, polygonWithHole, multiPolygon };
        GeometryCollection geometryCollection = geometryFactory.createGeometryCollection(geometries);

// Assignation des géométries à spatialLab
        spatialEvent.setMultiPoint(multiPoint);
        spatialEvent.setLineString(lineString1);
        spatialEvent.setPolygon(polygonWithHole);
        spatialEvent.setMultiLineString(multiLineString);
        spatialEvent.setMultiPolygon(multiPolygon);
        spatialEvent.setGeometryCollection(geometryCollection);

// Sauvegarde de spatialLab
        spatialEventRepository.save(spatialEvent);

    }
}
