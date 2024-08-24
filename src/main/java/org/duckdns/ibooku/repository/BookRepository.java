package org.duckdns.ibooku.repository;

import org.duckdns.ibooku.entity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
