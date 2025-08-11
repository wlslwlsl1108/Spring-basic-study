package com.p2.repository;

import com.p2.entity.Movie;
import com.p2.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByMovie(Movie movie);
    void deleteByMovie(Movie movie);
    Optional<Review> findByMovie_IdAndId(Long movieId, Long reviewId);
}
