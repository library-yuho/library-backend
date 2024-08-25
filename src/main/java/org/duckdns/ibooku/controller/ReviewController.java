package org.duckdns.ibooku.controller;

import lombok.RequiredArgsConstructor;
import org.duckdns.ibooku.dto.request.review.ReviewRequestDTO;
import org.duckdns.ibooku.dto.response.review.ReviewResponseDTO;
import org.duckdns.ibooku.service.review.ReviewService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/write")
    public boolean write(@RequestBody ReviewRequestDTO reviewRequest) {
        reviewService.write(reviewRequest);
        return true;
    }
}