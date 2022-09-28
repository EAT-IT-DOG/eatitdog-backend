package com.eatitdog.eatitdog.food.domain.entity;

import com.eatitdog.eatitdog.food.domain.enums.FoodType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "food")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Food {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private FoodType foodType;

    @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
    private String eatingMethod;

    @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
    private String symptom;
}
