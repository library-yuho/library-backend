package org.duckdns.ibooku.repository;

import org.duckdns.ibooku.entity.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
