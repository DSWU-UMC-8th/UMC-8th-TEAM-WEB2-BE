package umc.reviewinclass.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.reviewinclass.apiPayload.ApiResponse;
import umc.reviewinclass.domain.Lecture;
import umc.reviewinclass.service.LectureCommandService;
import umc.reviewinclass.web.dto.LectureRequestDTO;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "미션 API", description = "미션 관련 API 입니다.")
public class LectureController {
    private final LectureCommandService lectureCommandService;

    @PostMapping("/api/lecture/create")
    @Operation(summary = "강의 정보 입력", description = "강의 정보 입력 API입니다.")
    public ApiResponse<Lecture> createLecture(
            @RequestBody @Valid LectureRequestDTO.createLectureDTO request
    ){
        Lecture response = lectureCommandService.createLecture(request);
        return ApiResponse.onSuccess(response);
    }
}
