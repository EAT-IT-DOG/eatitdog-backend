package com.eatitdog.eatitdog.domain.dog.domain;

import com.eatitdog.eatitdog.domain.dog.enums.Sex;
import com.eatitdog.eatitdog.domain.user.domain.User;
import com.eatitdog.eatitdog.global.jpa.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "dog")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dog extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_breed_id", nullable = false)
    private Breed breed;

    @Builder
    public Dog(String name, LocalDate birthDate, Sex sex, User user, Breed breed) {
        this.name = name;
        this.birthDate = birthDate;
        this.sex = sex;
        this.user = user;
        this.breed = breed;
    }
}
