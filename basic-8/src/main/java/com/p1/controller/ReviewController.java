package com.p1.controller;

import com.p1.dto.ReviewRequest;
import com.p1.dto.ReviewResponse;
import com.p1.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController         // REST 컨트롤러
@RequiredArgsConstructor   // 생성자 주입
public class ReviewController {

    //서비스 의존성
    private final ReviewService reviewService;

    // CRUD의 [C] -> 해당 영화에 리뷰 생성(저장)
    @PostMapping("/movies/{movieId}/reviews")
    public ResponseEntity<ReviewResponse> saveReview(
            @RequestBody ReviewRequest request,
            @PathVariable Long movieId
            ){
        //서비스 호출
        return ResponseEntity.ok(reviewService.save(request, movieId));
    }
}
