package org.duckdns.ibooku.dto.response.book;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookResponseDTO {
    private String name;
    private String author;
    private String publisher;
    private String content;
    private double point;

    @Builder
    public BookResponseDTO(String name, String author, String publisher, String content, double point) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.content = content;
        this.point = point;
    }
}
