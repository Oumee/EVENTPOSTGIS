package com.EVENTPOSTGIS.EVENTPOSGIS.Models.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.*;


import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "spatialentity")
public class SpatialEvent
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private String location;
    private String communityName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Column(name = "point")
    private Point point;
    @Column(name = "multipoint")
    private MultiPoint multiPoint;

    @Column(name = "Linestring")
    private LineString LineString;

    @Column(name = "multilinestring")
    private MultiLineString multiLineString;

    @Column(name = "polygon")
    private Polygon polygon;

    @Column(name = "multipolygon")
    private MultiPolygon multiPolygon;

    @Column(name = "geocollection")
    private GeometryCollection geometryCollection;


}
