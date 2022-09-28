package com.eatitdog.eatitdog.user.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Size(max = 100)
    private String name;

    @Column(nullable = false, unique = true)
    @Size(max = 255)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String image;
}
