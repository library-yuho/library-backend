package org.duckdns.ibooku.controller;

import lombok.RequiredArgsConstructor;
import org.duckdns.ibooku.dto.response.book.BookResponseDTO;
import org.duckdns.ibooku.service.book.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/search")
    public List<BookResponseDTO> search(@RequestParam String keyword) {
        return bookService.search(keyword);
    }

    @GetMapping("/info")
    public BookResponseDTO info(@RequestParam String isbn) {
        return bookService.info(isbn);
    }
}
