package org.duckdns.ibooku.service.review;

import lombok.RequiredArgsConstructor;
import org.duckdns.ibooku.dto.response.review.ReviewResponseDTO;
import org.duckdns.ibooku.entity.review.Review;
import org.duckdns.ibooku.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<ReviewResponseDTO> list(String isbn, String email, boolean isSpoiler, String sortType) {
        List<Review> reviews;

        switch(sortType) {
            case "desc":
                reviews = reviewRepository.findReviewsByIsbnAndIsSpoilerOrderByPointDesc(isbn, isSpoiler);
                break;
            case "asc":
                reviews = reviewRepository.findReviewsByIsbnAndIsSpoilerOrderByPointAsc(isbn, isSpoiler);
                break;
            case "new":
            default:
                reviews = reviewRepository.findReviewsByIsbnAndIsSpoilerOrderByIdDesc(isbn, isSpoiler);
                break;
        }

        return reviews.stream()
                .map(review -> ReviewResponseDTO.builder()
                        .id(review.getId())
                        .point(review.getPoint())
                        .email(review.getUser().getEmail())
                        .nickname(review.getUser().getNickname())
                        .content(review.getContent())
                        .isSpoiler(review.isSpoiler())
                        .createdAt(review.getCreatedAt())
                        .isWriter(review.getUser().getEmail().equals(email) ? true : false)
                        .build())
                .collect(Collectors.toList());
    }
}
