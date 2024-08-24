package org.duckdns.ibooku.repository;

import org.duckdns.ibooku.entity.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query(value = "SELECT * FROM review WHERE book_id = (SELECT id FROM book WHERE isbn = :isbn) AND is_spoiler = :isSpoiler ORDER BY id DESC", nativeQuery = true)
    List<Review> findReviewsByIsbnAndIsSpoilerOrderByIdDesc(@Param("isbn") String isbn, @Param("isSpoiler") boolean isSpoiler);

    @Query(value = "SELECT * FROM review WHERE book_id = (SELECT id FROM book WHERE isbn = :isbn) AND is_spoiler = :isSpoiler ORDER BY point DESC", nativeQuery = true)
    List<Review> findReviewsByIsbnAndIsSpoilerOrderByPointDesc(@Param("isbn") String isbn, @Param("isSpoiler") boolean isSpoiler);

    @Query(value = "SELECT * FROM review WHERE book_id = (SELECT id FROM book WHERE isbn = :isbn) AND is_spoiler = :isSpoiler ORDER BY point ASC", nativeQuery = true)
    List<Review> findReviewsByIsbnAndIsSpoilerOrderByPointAsc(@Param("isbn") String isbn, @Param("isSpoiler") boolean isSpoiler);
}
