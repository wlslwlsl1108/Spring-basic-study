package com.p1.service;

import com.p1.dto.ReviewRequest;
import com.p1.dto.ReviewResponse;
import com.p1.entity.Movie;
import com.p1.entity.Review;
import com.p1.repository.MovieRepository;
import com.p1.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service                   // 서비스 계층
@RequiredArgsConstructor   // 생성자 주입
public class ReviewService {

    private final ReviewRepository reviewRepository;   // 리뷰 레포지토리
    private final MovieRepository movieRepository;     // 영화 레포지토리

    // CRUD의 [C] -> 해당 영화에 리뷰 생성(저장)
    @Transactional
    public ReviewResponse save(ReviewRequest request, Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("그런 movieId의 movie는 없어요")// 영화 조회
        );
        Review review = new Review(
                request.getContent(),
                movie
        );
        Review savedReview = reviewRepository.save(review);
        return new ReviewResponse(
                savedReview.getId(),
                savedReview.getContent()
        );

    }
}
