package umc.reviewinclass.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import umc.reviewinclass.apiPayload.ApiResponse;
import umc.reviewinclass.domain.lecture.Lecture;
import umc.reviewinclass.service.LectureService.LectureCommandService;
import umc.reviewinclass.service.LectureService.LectureQueryService;
import umc.reviewinclass.service.ReviewService.ReviewQueryService;
import umc.reviewinclass.web.dto.lecture.LectureRatingSummaryDto;
import umc.reviewinclass.web.dto.lecture.LectureRequestDTO;
import umc.reviewinclass.web.dto.lecture.LectureResponseDTO;
import umc.reviewinclass.web.dto.lecture.LectureSearchResponseDTO;
import umc.reviewinclass.web.dto.review.ReviewListDTO;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "강의 API", description = "강의 관련 API 입니다.")
public class LectureController {
    private final LectureCommandService lectureCommandService;
    private final LectureQueryService lectureQueryService;
    private final ReviewQueryService reviewQueryService;

    @PostMapping(value = "/api/lecture/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "강의 정보 입력", description = "multipart/form-data로 보내야 합니다.")
    public ApiResponse<Lecture> createLecture(
            @RequestPart("request") @Valid LectureRequestDTO.createLectureDTO request,
            @RequestPart(required = false) List<MultipartFile> images
    ) {
        Lecture response = lectureCommandService.createLecture(request, images);
        return ApiResponse.onSuccess(response);
    }

    @GetMapping("/api/lecture/{lectureId}")
    @Operation(summary = "강의 정보 상세 조회", description = "강의 정보 상세 조회 API입니다.")
    public ApiResponse<LectureResponseDTO.LectureDTO> getLectureInfo(
            @PathVariable Long lectureId
    ) {
        return ApiResponse.onSuccess(lectureQueryService.getLectureInfo(lectureId));
    }

    @GetMapping("/api/lecture/search")
    @Operation(summary = "강의 검색", description = "강의 검색 API입니다.")
    public ResponseEntity<?> searchLectures(@RequestParam String query) {
        List<LectureSearchResponseDTO.LectureDTO> results = lectureQueryService.search(query);
        return ResponseEntity.ok(ApiResponse.onSuccess(results));
    }


    @GetMapping("api/lecture/{lectureId}/ratings/summary")
    @Operation(summary = "강의 평점 및 별점 갯수 조회", description = "강의 평점 및 별점 갯수 조회 API입니다.")
    public ResponseEntity<?> getLectureRatingSummary(@PathVariable Long lectureId) {
        LectureRatingSummaryDto result = lectureQueryService.getLectureRatingSummary(lectureId);
        return ResponseEntity.ok(ApiResponse.onSuccess(result));
    }

    @GetMapping("/api/lectures")
    @Operation(summary = "전체 강의 목록 조회 API", description = "전체 강의 목록 조회 API입니다.")
    public ApiResponse<List<Map<String, Object>>> getLectures() {
        List<Map<String, Object>> lectures = lectureQueryService.getLectures();
        return ApiResponse.onSuccess(lectures);
    }

    @GetMapping("api/lecture/{lectureId}/reviews")
    @Operation(summary = "특정 강의 리뷰 목록 조회 API", description = "특정 강의 리뷰 목록 조회 API입니다.")
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호 - 0번이 1페이지"),
            @Parameter(name = "ratingMin", description = "별점 최소값"),
            @Parameter(name = "ratingMax", description = "별점 최대값"),
            @Parameter(name = "sort", description = "정렬 필드 (추천순-recommend, 최신순-createdAt)")
    })
    public ApiResponse<ReviewListDTO> getLectureReviews(
            @PathVariable Long lectureId,
            @RequestParam(name = "sort", required = false, defaultValue = "createdAt") String sort,
            @RequestParam(name = "ratingMin", required = false) Double ratingMin,
            @RequestParam(name = "ratingMax", required = false) Double ratingMax,
            @RequestParam(name = "page", defaultValue = "0") int page
    ) {
        return ApiResponse.onSuccess(reviewQueryService.getLectureReviews(lectureId, ratingMin, ratingMax, sort, page));
    }

}
