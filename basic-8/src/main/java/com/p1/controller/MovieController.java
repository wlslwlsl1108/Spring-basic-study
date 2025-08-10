package com.p1.controller;

import com.p1.dto.MovieRequest;
import com.p1.dto.MovieResponse;
import com.p1.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// JSON 응답 컨트롤러
// 컴포넌트 스캔 대상 -> 스프링이 "빈"으로 등록
@RestController
// final 필드 자동 주입
@RequiredArgsConstructor
public class MovieController {

    // 서비스 의존성 선언 -> final = 반드시 생성자에서 주입!
    // @RequiredArgsConstructor 덕분에 생성자 코드작성 불필요
    private final MovieService movieService;

    // CRUD의 [C] -> 영화 생성(저장)
    @PostMapping("/movies")
    public ResponseEntity<MovieResponse> saveMovie(
            @RequestBody MovieRequest request
    ){
        // 서비스 호출 후, 결과반환
        return ResponseEntity.ok(movieService.save(request));
    }

    // CRUD의 [R] -> 영화 전체 조회
    @GetMapping("/movies")
    public ResponseEntity<List<MovieResponse>> getAllMovies(){
        return ResponseEntity.ok(movieService.findAll());
    }

    // CRUD의 [U] -> 영화 수정
    @PutMapping("/movies/{movieId}")
    public ResponseEntity<MovieResponse> updateMovie(
            @RequestBody MovieRequest movierequest,
            @PathVariable("movieId") Long movieId
    ){
        return ResponseEntity.ok(movieService.update(movieId, movierequest));
    }
}
