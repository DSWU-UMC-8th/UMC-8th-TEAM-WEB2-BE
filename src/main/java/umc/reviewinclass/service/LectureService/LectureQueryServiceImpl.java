package umc.reviewinclass.service.LectureService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.reviewinclass.apiPayload.code.status.ErrorStatus;
import umc.reviewinclass.apiPayload.exception.handler.LectureHandler;
import umc.reviewinclass.domain.lecture.Lecture;
import umc.reviewinclass.domain.lecture.LectureImage;
import umc.reviewinclass.domain.platform.Platform;
import umc.reviewinclass.repository.LectureRepository;
import umc.reviewinclass.web.dto.lecture.LectureResponseDTO;
import umc.reviewinclass.web.dto.lecture.LectureSearchResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureQueryServiceImpl implements LectureQueryService {

    private final LectureRepository lectureRepository;

    @Override
    public LectureResponseDTO.LectureDTO getLectureInfo(Long lectureId) {

        // 강의 찾기
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new LectureHandler(ErrorStatus.LECTURE_NOT_FOUND));

        // platform
        Platform platform = lecture.getPlatform();

        // 강의 썸네일 리스트
        List<String> imgUrls = lecture.getLectureImages().stream()
                .map(LectureImage::getLectureImageUrl)
                .toList();

        return LectureResponseDTO.LectureDTO.builder()
                .name(lecture.getName())
                .instructorName(lecture.getInstructorName())
                .level(lecture.getLevel())
                .category(lecture.getCategory())
                .platformName(platform.getName())
                .imgUrls(imgUrls)
                .build();
    }

    /**
     * 강의를 강의명으로 검색합니다.
     *
     * @param query
     * @return LectureDTO list
     */
    public List<LectureSearchResponseDTO.LectureDTO> search(String query) {
        List<LectureSearchResponseDTO.LectureDTO> lectures = lectureRepository.findByNameContaining(query).stream()
                .map(p -> LectureSearchResponseDTO.LectureDTO.builder()
                        .id(p.getLectureId())
                        .name(p.getName())
                        .instructor(p.getInstructorName())
                        .platform(p.getPlatform().getName())
                        .build())
                .toList();
        return lectures;
    }
}
