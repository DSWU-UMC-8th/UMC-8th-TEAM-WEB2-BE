package umc.reviewinclass.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.reviewinclass.apiPayload.ApiResponse;
import umc.reviewinclass.service.ReviewService.ReviewCommandService;
import umc.reviewinclass.service.ReviewService.ReviewQueryService;
import umc.reviewinclass.web.dto.review.ReviewCreateRequestDto;
import umc.reviewinclass.web.dto.review.ReviewCreateResponseDto;
import umc.reviewinclass.web.dto.review.ReviewLikeResponseDto;
import umc.reviewinclass.web.dto.review.ReviewResponseDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewCommandService reviewCommandService;

    // 리뷰 등록
    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody ReviewCreateRequestDto requestDto) {
        Long reviewId = reviewCommandService.createReview(requestDto);
        ReviewCreateResponseDto result = new ReviewCreateResponseDto(reviewId);
        return ResponseEntity.ok(ApiResponse.onSuccess(result));
    }

    @PostMapping("/{reviewId}/like")
    public ResponseEntity<?> likeReview(@PathVariable Long reviewId) {
        reviewCommandService.likeReview(reviewId);

        ReviewLikeResponseDto result = new ReviewLikeResponseDto(reviewId, "좋아요가 등록되었습니다");
        return ResponseEntity.ok(ApiResponse.onSuccess(result));
    }
}
