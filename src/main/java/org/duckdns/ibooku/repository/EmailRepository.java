package org.duckdns.ibooku.repository;

import org.duckdns.ibooku.entity.user.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;

public interface EmailRepository extends JpaRepository<Email, Integer> {
    @Query(value = "SELECT * FROM email e WHERE e.email = :email AND e.created_at >= :now ORDER BY e.id DESC LIMIT 1", nativeQuery = true)
    Email findLatestEmailByEmailWithinFiveMinutes(@Param("email") String email, @Param("now") ZonedDateTime now);
}
