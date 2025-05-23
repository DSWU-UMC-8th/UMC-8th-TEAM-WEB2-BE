package umc.reviewinclass.web.controller;

import io.swagger.v3.oas.annotations.Operation;
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
import umc.reviewinclass.web.dto.lecture.LectureRatingSummaryDto;
import umc.reviewinclass.web.dto.lecture.LectureRequestDTO;
import umc.reviewinclass.web.dto.lecture.LectureResponseDTO;
import umc.reviewinclass.web.dto.lecture.LectureSearchResponseDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "강의 API", description = "강의 관련 API 입니다.")
public class LectureController {
    private final LectureCommandService lectureCommandService;
    private final LectureQueryService lectureQueryService;

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

}
