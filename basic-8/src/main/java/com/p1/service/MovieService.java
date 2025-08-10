package com.p1.service;

import com.p1.dto.MovieRequest;
import com.p1.dto.MovieResponse;
import com.p1.entity.Movie;
import com.p1.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//서비스 계층
@Service
// final 필드 자동 주입
@RequiredArgsConstructor

public class MovieService {

    // 레포지토리 의존성
    private final MovieRepository movieRepository;

    @Transactional
    public MovieResponse save(MovieRequest request){
        Movie movie = new Movie(request.getTitle());
        Movie savedMovie = movieRepository.save(movie);
        return new MovieResponse(
                savedMovie.getId(),
                savedMovie.getTitle()
        );
    }
}
