package org.duckdns.ibooku.repository;

import org.duckdns.ibooku.entity.library.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibraryRepository extends JpaRepository<Library, Integer> {
    @Query(value = "SELECT * FROM library l WHERE l.lat BETWEEN :lat - 0.05 AND :lat + 0.05 AND l.lon BETWEEN :lon - 0.05 AND :lon + 0.05", nativeQuery = true)
    List<Library> findByLatLonRange(@Param("lat") double lat, @Param("lon") double lon);
}
