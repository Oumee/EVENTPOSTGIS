package com.EVENTPOSTGIS.EVENTPOSGIS.custom;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.locationtech.jts.geom.Geometry;
import org.ugeojson.builder.UltimateGeoJSONBuilder;
import org.ugeojson.model.geometry.GeometryDto;
import org.ugeojson.parser.util.JtsUGeojsonConversionUtil;

import java.io.IOException;

public class CustomUGeojsonSerializer extends JsonSerializer<Geometry> {


    @Override
    public void serialize(Geometry geometry, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        //convertir des objets de géométrie créés avec JTS (Java Topology Suite) en une structure de données intermédiaire (probablement un DTO)
        JtsUGeojsonConversionUtil jtsUGeojsonConversionUtil = new JtsUGeojsonConversionUtil();
        // convertit l'objet geometry en un GeometryDto (Data Transfer Object), qui est une représentation plus simple et indépendante de la géométrie, souvent utilisée pour la sérialisation ou la transmission de données.
        GeometryDto geometryDto = jtsUGeojsonConversionUtil.toGeometryDto(geometry);
        // UltimateGeoJSONBuilder : Un utilitaire ou un constructeur qui génère du GeoJSON à partir d'un objet GeometryDto.
        // Convertit le GeometryDto en une chaîne de caractères au format GeoJSON.
        String geoJSON = UltimateGeoJSONBuilder.getInstance().toGeoJSON(geometryDto);
             // Écrit la chaîne GeoJSON dans le flux JSON sans échappement, l'insérant telle quelle dans le document JSON généré.
        jsonGenerator.writeRawValue(geoJSON);
    }
}
