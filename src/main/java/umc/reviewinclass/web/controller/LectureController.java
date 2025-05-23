package umc.reviewinclass.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.reviewinclass.apiPayload.ApiResponse;
import umc.reviewinclass.domain.lecture.Lecture;
import umc.reviewinclass.service.LectureService.LectureCommandService;
import umc.reviewinclass.service.LectureService.LectureQueryService;
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

    @PostMapping("/api/lecture/create")
    @Operation(summary = "강의 정보 입력", description = "강의 정보 입력 API입니다.")
    public ApiResponse<Lecture> createLecture(
            @RequestBody @Valid LectureRequestDTO.createLectureDTO request
    ) {
        Lecture response = lectureCommandService.createLecture(request);
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
}
