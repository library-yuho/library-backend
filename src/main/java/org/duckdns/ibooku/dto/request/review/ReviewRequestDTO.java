package org.duckdns.ibooku.dto.request.review;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewRequestDTO {
    private String email;
    private String isbn;
    private String content;
    private boolean spoiler;
    private double point;
    private Double lat;
    private Double lon;

    @Builder
    public ReviewRequestDTO(String email, String isbn, String content, boolean spoiler, double point, Double lat, Double lon) {
        this.email = email;
        this.isbn = isbn;
        this.content = content;
        this.spoiler = spoiler;
        this.point = point;
        this.lat = lat;
        this.lon = lon;
    }
}