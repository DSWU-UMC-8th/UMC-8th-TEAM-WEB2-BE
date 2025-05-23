package umc.reviewinclass.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.reviewinclass.apiPayload.ApiResponse;
import umc.reviewinclass.service.ReviewService.ReviewCommandService;
import umc.reviewinclass.web.dto.review.ReviewCreateRequestDTO;
import umc.reviewinclass.web.dto.review.ReviewCreateResponseDTO;
import umc.reviewinclass.web.dto.review.ReviewLikeResponseDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
@Tag(name = "리뷰 API", description = "리뷰 관련 API 입니다.")
public class ReviewController {

    private final ReviewCommandService reviewCommandService;

    // 리뷰 등록
    @PostMapping
    @Operation(summary = "리뷰 등록", description = "리뷰를 등록 API 입니다.")
    public ResponseEntity<?> createReview(@RequestBody ReviewCreateRequestDTO requestDto) {
        Long reviewId = reviewCommandService.createReview(requestDto);
        ReviewCreateResponseDTO result = new ReviewCreateResponseDTO(reviewId);
        return ResponseEntity.ok(ApiResponse.onSuccess(result));
    }
    @PostMapping("/{reviewId}/like")
    public ResponseEntity<?> likeReview(@PathVariable Long reviewId) {
        reviewCommandService.likeReview(reviewId);

        ReviewLikeResponseDto result = new ReviewLikeResponseDto(reviewId, "좋아요가 등록되었습니다");
        return ResponseEntity.ok(ApiResponse.onSuccess(result));
    }

}