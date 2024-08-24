package org.duckdns.ibooku.service.book;

import lombok.RequiredArgsConstructor;
import org.duckdns.ibooku.dto.response.book.BookResponseDTO;
import org.duckdns.ibooku.entity.book.Book;
import org.duckdns.ibooku.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<BookResponseDTO> search(String keyword) {
        List<Book> result = bookRepository.search(keyword);

        return result.stream()
                .map(book -> BookResponseDTO.builder()
                        .name(book.getName())
                        .isbn(book.getIsbn())
                        .author(book.getAuthor())
                        .publisher(book.getPublisher())
                        .content(book.getContent())
                        .point(0.0)
                        .build())
                .collect(Collectors.toList());
    }
}
