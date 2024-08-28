package org.duckdns.ibooku.dto.response.review;

import lombok.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewResponseDTO {
    private int id;
    private String email;
    private String nickname;
    private String bookName;
    private String bookAuthor;
    private String isbn;
    private String content;
    private boolean spoiler;
    private Double lat;
    private Double lon;
    private double point;
    private boolean isWriter;
    private ZonedDateTime createdAt;

    @Builder
    public ReviewResponseDTO(int id, String email, String nickname, String bookName, String bookAuthor, String isbn, String content, boolean spoiler, Double lat, Double lon, double point, boolean isWriter, ZonedDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.isbn = isbn;
        this.content = content;
        this.spoiler = spoiler;
        this.lat = lat;
        this.lon = lon;
        this.point = point;
        this.isWriter = isWriter;
        this.createdAt = createdAt.withZoneSameInstant(ZoneId.of("Asia/Seoul"));
    }
}
