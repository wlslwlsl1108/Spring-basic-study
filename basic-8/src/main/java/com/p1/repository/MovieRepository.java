package com.p1.repository;

import com.p1.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
   // List<Movie> findAllTitle(Movie movie);
   // void deleteMovie(Long movieId);
}
