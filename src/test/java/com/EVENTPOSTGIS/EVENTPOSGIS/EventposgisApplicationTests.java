package com.EVENTPOSTGIS.EVENTPOSGIS;

import com.EVENTPOSTGIS.EVENTPOSGIS.Models.Entity.SpatialEvent;
import com.EVENTPOSTGIS.EVENTPOSGIS.Repository.SpatialEventRepository;
import org.geolatte.geom.jts.JTSUtils;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.ugeojson.builder.UltimateGeoJSONBuilder;
import org.ugeojson.model.GeoJSONObjectDto;
import org.ugeojson.model.geometry.GeometryDto;
import org.ugeojson.model.geometry.PointDto;
import org.ugeojson.model.geometry.PolygonDto;
import org.ugeojson.parser.UltimateGeoJSONParser;
import org.ugeojson.parser.util.JtsUGeojsonConversionUtil;

import java.awt.*;

@SpringBootTest
class EventposgisApplicationTests {

	@Autowired
	private SpatialEventRepository spatialEventRepository;

	@Test
	void conversionTest()
	{
		SpatialEvent spatialEvent = spatialEventRepository.findById(1).get();
		Point point = spatialEvent.getPoint();
		Polygon polygon = spatialEvent.getPolygon();

	JtsUGeojsonConversionUtil jtsUGeojsonConversionUtil = new JtsUGeojsonConversionUtil();
	// conversion du point
	PointDto pointDto = jtsUGeojsonConversionUtil.toPointDto(point);
	// conversion de polygone
	PolygonDto polygonDto = jtsUGeojsonConversionUtil.toPolygonDto(polygon);
    // conversion de tout type de geometrie
	GeometryDto geometryDto= jtsUGeojsonConversionUtil.toGeometryDto(spatialEvent.getGeometryCollection());

		String pointJson = UltimateGeoJSONBuilder.getInstance().toGeoJSON(pointDto);
		String polygonJson = UltimateGeoJSONBuilder.getInstance().toGeoJSON(polygonDto);
		System.out.println("----------------------pointJson-------------");
		System.out.println(pointJson);
		System.out.println("--------------polygonJson---------------");
	  	System.out.println(polygonJson);

	// convertir de GeoJson to JTS
		GeoJSONObjectDto parse = UltimateGeoJSONParser.getInstance().parse(polygonJson);
		Geometry geometry = jtsUGeojsonConversionUtil.toJtsGeometry((GeometryDto) parse);
	    System.out.println(geometry);

	}

}
