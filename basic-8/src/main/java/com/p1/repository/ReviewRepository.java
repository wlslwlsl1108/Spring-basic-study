package com.p1.repository;

import com.p1.entity.Movie;
import com.p1.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
