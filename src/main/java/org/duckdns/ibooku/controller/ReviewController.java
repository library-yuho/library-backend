package org.duckdns.ibooku.controller;

import lombok.RequiredArgsConstructor;
import org.duckdns.ibooku.dto.response.review.ReviewResponseDTO;
import org.duckdns.ibooku.service.review.ReviewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/list")
    public List<ReviewResponseDTO> list(
            @RequestParam String isbn,
            @RequestParam String email,
            @RequestParam boolean isSpoiler,
            @RequestParam String sortType) {
        return reviewService.list(isbn, email, isSpoiler, sortType);
    }
}