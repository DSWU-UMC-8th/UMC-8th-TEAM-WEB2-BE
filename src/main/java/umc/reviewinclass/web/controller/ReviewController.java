package umc.reviewinclass.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import umc.reviewinclass.apiPayload.ApiResponse;
import umc.reviewinclass.domain.enums.CategoryType;
import umc.reviewinclass.domain.enums.Level;
import umc.reviewinclass.domain.enums.StudyPeriod;
import umc.reviewinclass.service.ReviewService.ReviewCommandService;
import umc.reviewinclass.service.ReviewService.ReviewQueryService;
import umc.reviewinclass.web.dto.review.ReviewCreateRequestDTO;
import umc.reviewinclass.web.dto.review.ReviewCreateResponseDTO;
import umc.reviewinclass.web.dto.review.ReviewLikeResponseDto;
import umc.reviewinclass.web.dto.review.ReviewSearchListResponseDTO;
import umc.reviewinclass.web.dto.review.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name = "리뷰 API", description = "리뷰 관련 API 입니다.")
public class ReviewController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

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

    // 인기 리뷰 조회
    @GetMapping("/reviews/popular")
    @Operation(summary = "인기 리뷰 조회", description = "좋아요 순으로 정렬된 리뷰 목록을 조회합니다.")
    public ResponseEntity<?> getPopularReviews(@RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("likes").descending());
        Page<PopularReviewResponseDTO> result = reviewQueryService.getPopularReviews(pageable);
        return ResponseEntity.ok(ApiResponse.onSuccess(result));
    }

    // 최신 리뷰 조회
    @GetMapping("/reviews/latest")
    @Operation(summary = "최신 리뷰 조회", description = "등록일 기준 최신 리뷰 목록을 조회합니다.")
    public ResponseEntity<?> getLatestReviews(@RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("createdAt").descending());
        Page<LatestReviewResponseDTO> result = reviewQueryService.getLatestReviews(pageable);
        return ResponseEntity.ok(ApiResponse.onSuccess(result));
    }

    // 리뷰 필터링
    @GetMapping("/reviews/filter")
    @Operation(summary = "리뷰 필터링", description = "카테고리 / 난이도 / 수강기간 중 일부 또는 전체로 리뷰 필터링")
    public ResponseEntity<?> filterReviews(
            @RequestParam(required = false) CategoryType category,
            @RequestParam(required = false) Level level,
            @RequestParam(required = false) StudyPeriod period
    ) {
        List<PopularReviewResponseDTO> result = reviewQueryService.getFilteredReviews(category, level, period);
        return ResponseEntity.ok(ApiResponse.onSuccess(result));
    }
}

