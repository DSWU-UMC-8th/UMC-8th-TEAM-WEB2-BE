package umc.reviewinclass.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.reviewinclass.apiPayload.ApiResponse;
import umc.reviewinclass.service.ReviewService.ReviewCommandService;
import umc.reviewinclass.service.ReviewService.ReviewQueryService;
import umc.reviewinclass.web.dto.review.ReviewCreateRequestDTO;
import umc.reviewinclass.web.dto.review.ReviewCreateResponseDTO;
import umc.reviewinclass.web.dto.review.ReviewLikeResponseDto;
import org.springframework.data.domain.Pageable;
import umc.reviewinclass.web.dto.review.ReviewSearchListResponseDTO;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
@Tag(name = "리뷰 API", description = "리뷰 관련 API 입니다.")
public class ReviewController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

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

    // 리뷰 검색
    @GetMapping("/search")
    @Operation(summary = "리뷰 검색", description = "리뷰 검색 API 입니다.")
    public ResponseEntity<?> searchReviews(
            @Parameter(description = "리뷰 내용에 포함될 검색어")
            @RequestParam(required = false) String query,
            @Parameter(description = "별점 최소값 (ex. 3.5)")
            @RequestParam(required = false) Double minRating,
            @Parameter(description = "별점 최대값 (ex. 5.0)")
            @RequestParam(required = false) Double maxRating,
            @Parameter(description = "정렬 기준: latest(최신순), likes(추천순), 기본값은 latest")
            @RequestParam(defaultValue = "latest") String sort,
            @ParameterObject
            @PageableDefault(size = 5) Pageable pageable
    ) {
        Double min = (minRating != null) ? minRating : 0.0;
        Double max = (maxRating != null) ? maxRating : 5.0;

        Page<ReviewSearchListResponseDTO.ReviewDTO> resultPage =
                reviewQueryService.searchReviews(query, sort, min, max, pageable);

        ReviewSearchListResponseDTO response = ReviewSearchListResponseDTO.builder()
                .page(resultPage.getNumber())
                .size(resultPage.getSize())
                .totalCount(resultPage.getTotalElements())
                .hasNext(resultPage.hasNext())
                .reviews(resultPage.getContent())
                .build();

        return ResponseEntity.ok(ApiResponse.onSuccess(response));
    }
}

