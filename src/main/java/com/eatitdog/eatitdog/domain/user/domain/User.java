package com.eatitdog.eatitdog.domain.user.domain;

import com.eatitdog.eatitdog.domain.user.enums.UserStatus;
import com.eatitdog.eatitdog.domain.user.exception.PasswordNotMatchException;
import com.eatitdog.eatitdog.global.jpa.BaseTime;
import com.eatitdog.eatitdog.global.lib.encrypt.Encrypt;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class User extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Size(max = 100)
    private String name;

    @Column(nullable = false, unique = true)
    @Size(max = 255)
    private String email;

    @JsonIgnore
    private String password;

    private String image;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @PrePersist
    public void prePersist() {
        this.status = this.status == null ? UserStatus.ACTIVE : this.status;
    }

    public void updateUser(String name, String email, String image) {
        this.name = name.isBlank() ? this.name : name;
        this.email = email.isBlank() ? this.email : email;
        this.image = image.isBlank() ? this.image : image;
    }

    public void updatePassword(Encrypt encrypt, String previousPassword, String newPassword) {
        if (!encrypt.match(previousPassword, password)) {
            throw PasswordNotMatchException.EXCEPTION;
        }
        password = encrypt.encode(newPassword);
    }

    public void deactivateUser() {
        this.status = UserStatus.DEACTIVATED;
    }

    @Builder
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
