package com.EVENTPOSTGIS.EVENTPOSGIS.Controller;

import com.EVENTPOSTGIS.EVENTPOSGIS.Models.Entity.SpatialEvent;
import com.EVENTPOSTGIS.EVENTPOSGIS.Service.SpatialEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/spatial")
public class SpatialEventController {

    @Autowired
    private SpatialEventService spatialEventService;

    @GetMapping(value = "/{id}")
   public SpatialEvent getSpatialevent(@PathVariable Integer id){

       return spatialEventService.getSpatialEvent(id);

    }

    @PostMapping(value = "/save")
    public void saveEvent(@RequestBody SpatialEvent spatialEvent)
    {
        spatialEventService.saveSpatialEvent(spatialEvent);
    }

    @PostMapping(value = "/getAll")
    public List<SpatialEvent> getIntersectes(@RequestBody SpatialEvent spatialEvent)
    {
         return spatialEventService.getInsertected(spatialEvent);
    }

    @PostMapping(value = "/PlusProcheAvec/{distance}")
    public List<SpatialEvent> getNearWithinDistance(@RequestBody SpatialEvent spatialEvent, @PathVariable Double distance) {

        return spatialEventService.findNearWithinDistance(spatialEvent.getPoint(), distance);
    }

    @GetMapping("/getevent")
    public String getevent()
    {
        return "EVENT";
    }



}
