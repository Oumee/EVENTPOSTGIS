package com.EVENTPOSTGIS.EVENTPOSGIS.Service;


import com.EVENTPOSTGIS.EVENTPOSGIS.Models.Entity.SpatialEvent;
import com.EVENTPOSTGIS.EVENTPOSGIS.Repository.SpatialEventRepository;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class SpatialEventService {

    @Autowired
    private SpatialEventRepository spatialEventRepository;


 public SpatialEvent getSpatialEvent(Integer id)
 {
  return  spatialEventRepository.findById(id).get();
 }

 public void saveSpatialEvent(SpatialEvent spatialEvent)
 {
     spatialEventRepository.save(spatialEvent);
 }

    public List<SpatialEvent> getInsertected(SpatialEvent spatialEvent)
    {
   return spatialEventRepository.findItemsIntersects(spatialEvent.getPolygon());
     }

    public List<SpatialEvent> findNearWithinDistance(Point point, Double distance) {
      return spatialEventRepository.findNearWithinDistance(point, distance);
 }
}
