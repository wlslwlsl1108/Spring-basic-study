package com.p1.service;

import com.p1.dto.MovieRequest;
import com.p1.dto.MovieResponse;
import com.p1.dto.ReviewRequest;
import com.p1.dto.ReviewResponse;
import com.p1.entity.Movie;
import com.p1.entity.Review;
import com.p1.repository.MovieRepository;
import com.p1.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service                   // 서비스 계층
@RequiredArgsConstructor   // 생성자 주입
public class ReviewService {

    private final ReviewRepository reviewRepository;   // 리뷰 레포지토리
    private final MovieRepository movieRepository;     // 영화 레포지토리

    // CRUD의 [C] -> 해당 영화에 리뷰 생성(저장)
    @Transactional
    public ReviewResponse save(ReviewRequest request, Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("그런 movieId의 movie는 없어요.")// 영화 조회
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

    // CRUD의 [R] -> 해당 영화의 리뷰 전체 조회
    @Transactional(readOnly = true)
    public List<ReviewResponse> findAll(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("그런 movieId의 movie는 없어요.")
        );

        List<Review> movies = reviewRepository.findAllByMovie(movie);
        List<ReviewResponse> dtos = new ArrayList<>();

        for (Review review : movies) {
            dtos.add(
                    new ReviewResponse(
                            review.getId(),
                            review.getContent()
                    )
            );
        }
        return dtos;
    }

    // CRUD의 [R] -> 리뷰 단건 조회
    @Transactional(readOnly = true)
    public ReviewResponse findOne(Long movieId, Long reviewId) {
        Review review = reviewRepository.findByMovie_IdAndId(movieId, reviewId).orElseThrow(
                () -> new IllegalArgumentException("그런 movieId의 movie는 없어요.")
        );

        return new ReviewResponse(review.getId(), review.getContent());
    }

    // CRUD의 [U] -> 리뷰 수정
    // com/p1/service/ReviewService.java (메서드 추가)
    @Transactional
    public ReviewResponse update(Long movieId, Long reviewId, ReviewRequest request) {
        Review review = reviewRepository.findByMovie_IdAndId(movieId, reviewId)
                .orElseThrow(() -> new IllegalArgumentException("그런 id의 review는 없습니다."));
        review.updateContent(request.getContent()); // 변경 감지
        return new ReviewResponse(review.getId(), review.getContent());
    }

    // CRUD의 [D] -> 리뷰 삭제
    @Transactional
    public void delete(Long movieId, Long reviewId) {
        Review review = reviewRepository.findByMovie_IdAndId(movieId, reviewId).orElseThrow(
                () -> new IllegalArgumentException("그런 id의 review는 없습니다.")
        );
        reviewRepository.delete(review);

    }

}
