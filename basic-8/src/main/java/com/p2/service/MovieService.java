package com.p2.service;

import com.p2.dto.MovieRequest;
import com.p2.dto.MovieResponse;
import com.p2.entity.Movie;
import com.p2.repository.MovieRepository;
import com.p2.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;

    // CRUD의 [C] -> 영화 생성(저장)
    @Transactional
    public MovieResponse save(MovieRequest request) {
        Movie movie = new Movie(request.getTitle());
        Movie savedMovie = movieRepository.save(movie);
        return new MovieResponse(
                savedMovie.getId(),
                savedMovie.getTitle()
        );
    }

    // CRUD의 [R] -> 영화 전체 조회
    @Transactional
    public List<MovieResponse> findAll() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(m -> new MovieResponse(m.getId(), m.getTitle()))
                .toList();
    }

    // CRUD의 [R] -> 영화 단건 조회
    @Transactional
    public MovieResponse findById(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 movieId가 없습니다.")
        );
        return new MovieResponse(movie.getId(), movie.getTitle());
    }

    // CRUD의 [U] -> 영화 수정
    @Transactional
    public MovieResponse update(Long movieId, MovieRequest movieRequest) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 movieId가 없습니다.")
        );
        movie.updateTitle(movieRequest.getTitle());
        return new MovieResponse(
                movie.getId(),
                movie.getTitle()
        );
    }

    // CRUD의 [D] -> 영화 삭제
    @Transactional
    public void delete(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 movieId가 없습니다.")
        );
        reviewRepository.deleteByMovie(movie);
        movieRepository.delete(movie);
    }

}
