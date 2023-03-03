package com.eatitdog.eatitdog.domain.dog.domain;

import com.eatitdog.eatitdog.global.jpa.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "breed")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Breed extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @Builder
    public Breed(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
