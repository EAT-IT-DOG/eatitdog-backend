package com.eatitdog.eatitdog.domain.product.domain;

import com.eatitdog.eatitdog.domain.food.domain.Food;
import com.eatitdog.eatitdog.global.jpa.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseTimeEntity {

    @Id @Column(length = 50)
    private String id;

    @Column(nullable = false, length = 500)
    private String name;

    @Lob
    private String rawMaterials;

    @Column(length = 100)
    private String allergy;

    @Column(length = 50)
    private String kind;

    @Column(length = 300)
    private String capacity;

    @Column(length = 100)
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "fk_food_id")
    private Food food;

    @Builder
    public Product(String id, String name, String rawMaterials, String allergy, String kind, String capacity, String image) {
        this.id = id;
        this.name = name;
        this.rawMaterials = rawMaterials;
        this.allergy = allergy;
        this.kind = kind;
        this.capacity = capacity;
        this.image = image;
    }
}