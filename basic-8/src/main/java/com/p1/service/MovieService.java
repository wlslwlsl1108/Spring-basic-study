package com.p1.service;

import com.p1.dto.MovieRequest;
import com.p1.dto.MovieResponse;
import com.p1.entity.Movie;
import com.p1.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

//서비스 계층
@Service
// final 필드 자동 주입
@RequiredArgsConstructor

public class MovieService {

    // 레포지토리 의존성
    private final MovieRepository movieRepository;

    // CRUD의 [C] -> 영화 생성(저장)
    @Transactional
    public MovieResponse save(MovieRequest request){
        Movie movie = new Movie(request.getTitle());
        Movie savedMovie = movieRepository.save(movie);
        return new MovieResponse(
                savedMovie.getId(),
                savedMovie.getTitle()
        );
    }

    // CRUD의 [R] -> 영화 전체 조회
    @Transactional(readOnly = true)        // 조회 최적화
    public List<MovieResponse> findAll(){  // findAll() = JPA 기본 제공
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(m -> new MovieResponse(m.getId(), m.getTitle()))
                .toList();
    }
}
