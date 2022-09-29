package com.eatitdog.eatitdog.food.domain.entity;

import com.eatitdog.eatitdog.food.domain.enums.FoodStatus;
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

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private FoodStatus foodStatus;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String eatingMethod;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String symptom;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String benefit;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String caution;
}
