package com.eatitdog.eatitdog.domain.apply.domain.entity;

import com.eatitdog.eatitdog.domain.food.domain.enums.FoodSafeness;
import com.eatitdog.eatitdog.domain.food.domain.enums.FoodType;
import com.eatitdog.eatitdog.domain.user.domain.entity.User;
import com.eatitdog.eatitdog.global.jpa.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "apply")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Apply extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String foodName;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private FoodType type;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private FoodSafeness safeness;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String eatingMethod;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String symptom;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String benefit;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String caution;

    @Builder
    public Apply(User user, String foodName, FoodType type, FoodSafeness safeness, String eatingMethod, String symptom, String benefit, String caution) {
        this.user = user;
        this.foodName = foodName;
        this.type = type;
        this.safeness = safeness;
        this.eatingMethod = eatingMethod;
        this.symptom = symptom;
        this.benefit = benefit;
        this.caution = caution;
    }
}
