package com.eatitdog.eatitdog.domain.report.domain;

import com.eatitdog.eatitdog.domain.product.domain.Product;
import com.eatitdog.eatitdog.global.jpa.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "report")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name="fk_product_id")
    private Product product;

    @Column(name = "count")
    private long count;

    public void increaseCount() {
        this.count++;
    }

    public Report(Product product) {
        this.product = product;
    }
}
