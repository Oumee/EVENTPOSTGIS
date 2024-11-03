package com.EVENTPOSTGIS.EVENTPOSGIS.Repository;

import com.EVENTPOSTGIS.EVENTPOSGIS.Models.Entity.SpatialEvent;
import org.locationtech.jts.geom.Geometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SpatialEventRepository extends JpaRepository<SpatialEvent, Integer> {

    // filtrage par zone ( polygone)
    @Query("SELECT s FROM SpatialEvent s WHERE ST_Intersects(s.polygon, :filter) = true")
    List<SpatialEvent> findItemsIntersects(@Param("filter") Geometry filter);

    @Query("SELECT s FROM SpatialEvent s WHERE ST_DistanceSphere(s.polygon, :filter) <= :distance")
    List<SpatialEvent> findNearWithinDistance(@Param("filter") Geometry filter, @Param("distance") Double distance);


}
