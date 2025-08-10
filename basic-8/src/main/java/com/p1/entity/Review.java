package com.p1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 자동 증가
    private Long id;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)                  // 다대일
    @JoinColumn(name = "movie_id", nullable = false)    // FK
    private Movie movie;

    public Review(String content, Movie movie) {
        this.content = content;
        this.movie = movie;
    }

}
