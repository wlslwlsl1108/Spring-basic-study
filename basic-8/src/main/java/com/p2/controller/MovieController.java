package com.p2.controller;

import com.p2.dto.MovieRequest;
import com.p2.dto.MovieResponse;
import com.p2.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    // CRUD의 [C] -> 영화 생성(저장)
    @PostMapping("/movies")
    public ResponseEntity<MovieResponse> saveMovie(
            @RequestBody MovieRequest request
    ){
        return ResponseEntity.ok(movieService.save(request));
    }

    // CRUD의 [R] -> 영화 전체 조회
    @GetMapping("/movies")
    public ResponseEntity<List<MovieResponse>> getAllMovies(){
        return ResponseEntity.ok(movieService.findAll());
    }

    // CRUD의 [R] -> 영화 단건 조회
    @GetMapping("/movies/{movieId}")
    public ResponseEntity<MovieResponse> getMovie(
            @PathVariable Long movieId
    ){
        return ResponseEntity.ok(movieService.findById(movieId));
    }

    // CRUD의 [U] -> 영화 수정
    @PutMapping("/movies/{movieId}")
    public ResponseEntity<MovieResponse> updateMovie(
            @RequestBody MovieRequest movierequest,
            @PathVariable Long movieId
    ){
        return ResponseEntity.ok(movieService.update(movieId, movierequest));
    }

    // CRUD의 [D] -> 영화 삭제
    @DeleteMapping("/movies/{movieId}")
    public ResponseEntity<Void> deleteMovie(
            @PathVariable Long movieId
    ){
        movieService.delete(movieId);
        return ResponseEntity.noContent().build();
    }
}
