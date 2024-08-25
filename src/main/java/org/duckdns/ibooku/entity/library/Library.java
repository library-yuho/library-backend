package org.duckdns.ibooku.entity.library;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "LIBRARY")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(length = 50)
    private String name;

    @NotNull
    @Column(length = 50)
    private String libCode;

    @NotNull
    @Column(length = 200)
    private String address;

    @NotNull
    @Column(length = 500)
    private String content;

    @NotNull
    @Column(length = 200)
    private String telephone;

    @NotNull
    @Column(length = 500)
    private String website;

    private Double lat;

    private Double lon;

    @Builder
    public Library(int id, String name, String libCode, String address, String content, String telephone, String website, Double lat, Double lon) {
        this.id = id;
        this.name = name;
        this.libCode = libCode;
        this.address = address;
        this.content = content;
        this.telephone = telephone;
        this.website = website;
        this.lat = lat;
        this.lon = lon;
    }
}
