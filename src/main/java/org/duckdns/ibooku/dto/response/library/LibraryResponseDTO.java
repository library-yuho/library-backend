package org.duckdns.ibooku.dto.response.library;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LibraryResponseDTO {
    private int id;
    private String name;
    private String libCode;
    private String address;
    private String content;
    private String telephone;
    private String website;
    private Double lat;
    private Double lon;

    @Builder
    public LibraryResponseDTO(int id, String name, String libCode, String address, String content, String telephone, String website, Double lat, Double lon) {
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
