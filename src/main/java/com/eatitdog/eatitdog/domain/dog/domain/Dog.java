package com.eatitdog.eatitdog.domain.dog.domain;

import com.eatitdog.eatitdog.domain.dog.enums.Sex;
import com.eatitdog.eatitdog.domain.dog.exception.DogNotOwnerException;
import com.eatitdog.eatitdog.domain.user.domain.User;
import com.eatitdog.eatitdog.global.jpa.BaseTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_breed_id", nullable = false)
    private Breed breed;

    public void injectUser(User user) {
        this.user = user;
    }

    public void injectBreed(Breed breed) {
        this.breed = breed;
    }

    public void checkOwner(User user) {
        if (user.getId() != this.user.getId()) {
            throw DogNotOwnerException.EXCEPTION;
        }
    }

    @Builder
    public Dog(String name, LocalDate birthDate, Sex sex) {
        this.name = name;
        this.birthDate = birthDate;
        this.sex = sex;
    }
}
