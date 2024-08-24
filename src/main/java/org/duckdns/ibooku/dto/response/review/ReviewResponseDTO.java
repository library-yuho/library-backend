package org.duckdns.ibooku.dto.response.review;

import lombok.*;

import java.time.ZonedDateTime;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewResponseDTO {
    private int id;
    private String email;
    private String nickname;
    private String content;
    private boolean isSpoiler;
    private double point;
    private boolean isWriter;
    private ZonedDateTime createdAt;

    @Builder
    public ReviewResponseDTO(int id, String email, String nickname, String content, boolean isSpoiler, double point, boolean isWriter, ZonedDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.content = content;
        this.isSpoiler = isSpoiler;
        this.point = point;
        this.isWriter = isWriter;
        this.createdAt = createdAt;
    }
}