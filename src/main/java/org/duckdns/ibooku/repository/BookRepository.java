package org.duckdns.ibooku.repository;

import org.duckdns.ibooku.entity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM Book b WHERE b.name LIKE %:keyword% OR b.author LIKE %:keyword% OR b.publisher LIKE %:keyword%")
    List<Book> search(@Param("keyword") String keyword);
}
