package com.EVENTPOSTGIS.EVENTPOSGIS.custom;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.locationtech.jts.geom.Geometry;
import org.ugeojson.model.GeoJSONObjectDto;
import org.ugeojson.model.geometry.GeometryDto;
import org.ugeojson.parser.UltimateGeoJSONParser;
import org.ugeojson.parser.util.JtsUGeojsonConversionUtil;

import java.io.IOException;

public class CustomUGeojsonDeserializer<T extends Geometry> extends JsonDeserializer<T> {
    @Override
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        // Accès facile aux données encapsulées dans le JSON
        // jsonParser pour lire l'entrée JSON et la convertir en un nœud JSON (JsonNode). JsonNode est une structure qui représente l'arbre d'objet JSON de manière hiérarchique.
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

       // convertit le nœud JSON en une chaîne GeoJSON
        String geojson = node.toString();

        // UltimateGeoJSONParser est utilisé pour analyser la chaîne GeoJSON. Il crée un objet GeoJSONObjectDto qui représente le contenu analysé du GeoJSON.
        GeoJSONObjectDto geoJSONObjectDto = UltimateGeoJSONParser.getInstance().parse(geojson);

        // JtsUGeojsonConversionUtil est un utilitaire qui convertit le GeoJSONObjectDto en un objet Geometry de la bibliothèque JTS
        JtsUGeojsonConversionUtil jtsUGeojsonConversionUtil = new JtsUGeojsonConversionUtil();
        Geometry geometry = jtsUGeojsonConversionUtil.toJtsGeometry((GeometryDto) geoJSONObjectDto);

        return (T) geometry;
    }
}
