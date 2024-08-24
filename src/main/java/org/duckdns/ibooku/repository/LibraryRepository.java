package org.duckdns.ibooku.repository;

import org.duckdns.ibooku.entity.library.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Integer> {

}
