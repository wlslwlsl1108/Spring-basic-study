package com.memberp1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // "생성자" -> DB에서 만들어주는 id 제외한 나머지
    public Member(String name) {
        // id는 DB에서 만들어줌 -> name 만 해주면 됨
        this.name = name;
    }

    public void updateName(String name){
        this.name = name;
    }
}
