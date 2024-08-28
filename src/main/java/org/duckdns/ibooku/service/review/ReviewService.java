package org.duckdns.ibooku.service.review;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.duckdns.ibooku.dto.request.review.ReviewRequestDTO;
import org.duckdns.ibooku.dto.response.review.ReviewResponseDTO;
import org.duckdns.ibooku.entity.review.Review;
import org.duckdns.ibooku.entity.user.User;
import org.duckdns.ibooku.repository.ReviewRepository;
import org.duckdns.ibooku.repository.UserRepository;
import org.duckdns.ibooku.util.JSONUtils;
import org.duckdns.ibooku.util.network.Get;
import org.duckdns.ibooku.util.network.Header;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    @Value("${api-key.nl}")
    private String KEY_NL;

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public List<ReviewResponseDTO> list(String isbn, String email, boolean spoiler, String sortType) {
        List<Review> reviews;

        switch(sortType) {
            case "desc":
                reviews = reviewRepository.findReviewsByIsbnAndIsSpoilerOrderByPointDesc(isbn, spoiler);
                break;
            case "asc":
                reviews = reviewRepository.findReviewsByIsbnAndIsSpoilerOrderByPointAsc(isbn, spoiler);
                break;
            case "new":
            default:
                reviews = reviewRepository.findReviewsByIsbnAndIsSpoilerOrderByIdDesc(isbn, spoiler);
                break;
        }

        return reviews.stream()
                .map(review -> ReviewResponseDTO.builder()
                        .id(review.getId())
                        .point(review.getPoint())
                        .email(review.getUser().getEmail())
                        .isbn(review.getIsbn())
                        .nickname(review.getUser().getNickname())
                        .content(review.getContent())
                        .spoiler(review.isSpoiler())
                        .createdAt(review.getCreatedAt())
                        .isWriter(review.getUser().getEmail().equals(email) ? true : false)
                        .build())
                .collect(Collectors.toList());
    }

    public void write(ReviewRequestDTO reviewRequest) {
        User user = userRepository.findByEmail(reviewRequest.getEmail());

        Review review = Review.builder()
                .user(user)
                .isbn(reviewRequest.getIsbn())
                .content(reviewRequest.getContent())
                .lat(reviewRequest.getLat())
                .lon(reviewRequest.getLon())
                .point(reviewRequest.getPoint())
                .spoiler(reviewRequest.isSpoiler())
                .build();

        reviewRepository.save(review);
    }

    public List<ReviewResponseDTO> place(String email, double lat, double lon) {
        return reviewRepository.findByLatLonRange(lat, lon).stream()
                .map(review -> {
                    String isbn = review.getIsbn();

                    String url = String.format("https://www.nl.go.kr/NL/search/openApi/search.do?key=%s&systemType=%s&pageNum=1&pageSize=30&apiType=json&detailSearch=true&isbnOp=isbn&isbnCode=%s", KEY_NL, "오프라인자료", isbn);

                    Header header = new Header();

                    try {
                        Get get = new Get(url)
                                .setHeader(header)
                                .execute();

                        int responseCode = get.getResponseCode();
                        if (responseCode != org.apache.http.HttpStatus.SC_OK) {
                            log.debug("responseCode: {}", responseCode);
                            throw new RuntimeException("통신 오류: " + get.getUrl());
                        }

                        JsonObject jsonObject = JSONUtils.parse(get.getResult());
                        JsonArray result = jsonObject.getAsJsonArray("result");

                        for (Object o: result) {
                            JsonObject book = (JsonObject) o;

                            String name = book.get("titleInfo").getAsString();
                            String image = book.get("imageUrl").getAsString();
                            String subject = book.get("kdcName1s").getAsString();
                            String author = book.get("authorInfo").getAsString();
                            String publisher = book.get("pubInfo").getAsString();
                            String content = "";
                            Double point = 0.0;

                            return ReviewResponseDTO.builder()
                                    .id(review.getId())
                                    .point(review.getPoint())
                                    .email(review.getUser().getEmail())
                                    .bookName(name)
                                    .bookAuthor(author)
                                    .isbn(review.getIsbn())
                                    .nickname(review.getUser().getNickname())
                                    .content(review.getContent())
                                    .lat(review.getLat())
                                    .lon(review.getLon())
                                    .spoiler(review.isSpoiler())
                                    .createdAt(review.getCreatedAt())
                                    .isWriter(review.getUser().getEmail().equals(email) ? true : false)
                                    .build();
                        }
                    } catch(Exception e) {
                        e.printStackTrace();
                        log.error("통신 오류가 발생했습니다.");
                        throw new RuntimeException();
                    }

                    return null;
                })
                .collect(Collectors.toList());
    }
}
