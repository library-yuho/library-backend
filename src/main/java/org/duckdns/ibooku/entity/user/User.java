package org.duckdns.ibooku.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.duckdns.ibooku.entity.BaseEntity;

@Entity
@Table(name = "USER")
@Getter
@Setter
@ToString(exclude = "password")
@NoArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(length = 200)
    private String email;

    @NotNull
    @Column(length = 200)
    private String password;

    @NotNull
    @Column(length = 2)
    private String gender;

    @NotNull
    @Column(length = 10)
    private String birth;

    @NotNull
    @Column(length = 200)
    private String nickname;

    @Builder
    public User(String email, String password, String gender, String birth, String nickname) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birth = birth;
        this.nickname = nickname;
    }
}
