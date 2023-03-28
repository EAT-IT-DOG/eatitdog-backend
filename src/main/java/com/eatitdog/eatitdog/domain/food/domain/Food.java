package com.eatitdog.eatitdog.domain.food.domain;

import com.eatitdog.eatitdog.domain.food.enums.FoodSafeness;
import com.eatitdog.eatitdog.domain.food.enums.FoodType;
import com.eatitdog.eatitdog.global.jpa.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "food")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Food extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private FoodType type;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private FoodSafeness safeness;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String eatingMethod; // 급여 방법

    @Column(columnDefinition = "MEDIUMTEXT")
    private String symptom; // 증상

    @Column(columnDefinition = "MEDIUMTEXT")
    private String benefit; // 주성분 및 기능

    @Column(columnDefinition = "MEDIUMTEXT")
    private String caution; // 주의사항

    private long searchCount;

    public void increaseSearchCount() {
        searchCount++;
    }

    @Builder
    public Food(String name, FoodType type, FoodSafeness safeness, String eatingMethod, String symptom, String benefit, String caution) {
        this.name = name;
        this.type = type;
        this.safeness = safeness;
        this.eatingMethod = eatingMethod;
        this.symptom = symptom;
        this.benefit = benefit;
        this.caution = caution;
    }
}
