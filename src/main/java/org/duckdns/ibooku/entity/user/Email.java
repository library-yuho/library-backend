package org.duckdns.ibooku.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.duckdns.ibooku.entity.BaseEntity;

@Entity
@Table(name = "EMAIL")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Email extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(length = 200)
    private String email;

    @NotNull
    @Column(length = 50)
    private String code;

    @Builder
    public Email(int id, String email, String code) {
        this.id = id;
        this.email = email;
        this.code = code;
    }
}