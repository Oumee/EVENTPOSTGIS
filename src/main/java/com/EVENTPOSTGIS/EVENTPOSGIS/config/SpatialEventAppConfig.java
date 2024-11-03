package com.EVENTPOSTGIS.EVENTPOSGIS.config;

import com.EVENTPOSTGIS.EVENTPOSGIS.custom.CustomUGeojsonDeserializer;
import com.EVENTPOSTGIS.EVENTPOSGIS.custom.CustomUGeojsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.springframework.boot.autoconfigure.task.TaskSchedulingProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpatialEventAppConfig {

    @Bean
    public ObjectMapper get0bjectMapper() {

        // d'ObjectMapper, un composant de la bibliothèque Jackson utilisé pour convertir des objets Java en JSON et inversement.
        ObjectMapper objectMapper = new ObjectMapper();

        // SimpleModule est une classe de Jackson permettant de définir des serializers et des deserializers personnalisés
        SimpleModule simpleModule = new SimpleModule();

        // definir serializer perso.
        simpleModule.addSerializer(Geometry.class, new CustomUGeojsonSerializer());
        // definir deserializer perso.
        simpleModule.addDeserializer(Polygon.class, new CustomUGeojsonDeserializer<>());
        simpleModule.addDeserializer(LineString.class, new CustomUGeojsonDeserializer<>());
        simpleModule.addDeserializer(Point.class, new CustomUGeojsonDeserializer<>());
        // On enregistre le simpleModule dans l'ObjectMapper. Cela permet à Jackson d'utiliser le serializer personnalisé pour tous les objets de type Geometry
        objectMapper.registerModule(simpleModule);

        return objectMapper;

    }
}
