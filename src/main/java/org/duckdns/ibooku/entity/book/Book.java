package org.duckdns.ibooku.entity.book;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.duckdns.ibooku.entity.BaseEntity;
import org.duckdns.ibooku.entity.library.Library;

@Entity
@Table(name = "BOOK")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id", nullable = false)
    private Library library;

    private int code;

    @NotNull
    @Column(length = 500)
    private String name;

    @NotNull
    @Column(length = 200)
    private String author;

    @NotNull
    @Column(length = 200)
    private String publisher;

    private int publishYear;

    @NotNull
    @Column(length = 50)
    private String isbn;

    @NotNull
    @Column(length = 50)
    private String subjectNo;

    private int count;

    private int checkout;

    @Builder
    public Book(int id, Library library, int code, String name, String author, String publisher, int publishYear, String isbn, String subjectNo, int count, int checkout) {
        this.id = id;
        this.library = library;
        this.code = code;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.isbn = isbn;
        this.subjectNo = subjectNo;
        this.count = count;
        this.checkout = checkout;
    }
}
