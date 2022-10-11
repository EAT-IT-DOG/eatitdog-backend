package com.eatitdog.eatitdog.domain.apply.domain.entity;

import com.eatitdog.eatitdog.domain.user.domain.entity.User;
import com.eatitdog.eatitdog.global.jpa.BaseTime;
import lombok.AccessLevel;
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

}
