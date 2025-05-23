package umc.reviewinclass.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import umc.reviewinclass.apiPayload.ApiResponse;
import umc.reviewinclass.service.ReviewService.ReviewCommandService;
import umc.reviewinclass.web.dto.review.ReviewCreateRequestDTO;
import umc.reviewinclass.web.dto.review.ReviewCreateResponseDTO;
import umc.reviewinclass.web.dto.review.ReviewLikeResponseDto;

@RequiredArgsConstructor
@RestController
@Tag(name = "리뷰 API", description = "리뷰 관련 API 입니다.")
public class ReviewController {

    private final ReviewCommandService reviewCommandService;

    // 리뷰 등록
    @PostMapping(value = "/reviews", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "리뷰 등록", description = "multipart/form-data로 보내야 합니다.")
    public ResponseEntity<?> createReview(
            @RequestPart("request") @Valid ReviewCreateRequestDTO requestDto,
            @RequestPart(required = false) MultipartFile image

    ) {
        Long reviewId = reviewCommandService.createReview(requestDto, image);
        ReviewCreateResponseDTO result = new ReviewCreateResponseDTO(reviewId);
        return ResponseEntity.ok(ApiResponse.onSuccess(result));
    }

    @PostMapping("/reviews/{reviewId}/like")
    public ResponseEntity<?> likeReview(@PathVariable Long reviewId) {
        reviewCommandService.likeReview(reviewId);

        ReviewLikeResponseDto result = new ReviewLikeResponseDto(reviewId, "좋아요가 등록되었습니다");
        return ResponseEntity.ok(ApiResponse.onSuccess(result));
    }
}

