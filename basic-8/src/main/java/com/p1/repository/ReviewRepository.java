package com.p1.repository;

import com.p1.entity.Movie;
import com.p1.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByMovie(Movie movie);
    void deleteByMovie(Movie movie);
    Optional<Review> findByMovie_IdAndId(Long movieId, Long reviewId);
}
