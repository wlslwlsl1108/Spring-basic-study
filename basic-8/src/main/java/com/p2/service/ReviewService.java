package com.p2.service;

import com.p2.dto.ReviewRequest;
import com.p2.dto.ReviewResponse;
import com.p2.entity.Movie;
import com.p2.entity.Review;
import com.p2.repository.MovieRepository;
import com.p2.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    // CRUD의 [C] -> 해당 영화에 리뷰 생성(저장)
    @Transactional
    public ReviewResponse save(ReviewRequest requset, Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                ()-> new IllegalArgumentException("그런 movieId의 movie는 없어요.")
        );
        Review review = new Review(
                requset.getContent(),
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
    @Transactional
    public ReviewResponse update(Long movieId, Long reviewId, ReviewRequest request) {
        Review review = reviewRepository.findByMovie_IdAndId(movieId, reviewId).orElseThrow(
                () -> new IllegalArgumentException("그런 id의 review는 없습니다.")
        );
        review.updateContent(request.getContent());
        return new ReviewResponse(
                review.getId(),
                review.getContent()
        );
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
