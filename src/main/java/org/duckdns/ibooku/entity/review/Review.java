package org.duckdns.ibooku.entity.review;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.*;
import org.duckdns.ibooku.entity.BaseEntity;
import org.duckdns.ibooku.entity.book.Book;
import org.duckdns.ibooku.entity.user.User;

@Entity
@Table(name = "REVIEW")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @NotNull
    @Column(length = 2048)
    private String content;

    private Double lat;

    private Double lon;

    private int point;

    private boolean isSpoiler;

    @Builder
    public Review(int id, User user, Book book, String content, Double lat, Double lon, int point, boolean isSpoiler) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.content = content;
        this.lat = lat;
        this.lon = lon;
        this.point = point;
        this.isSpoiler = isSpoiler;
    }
}
