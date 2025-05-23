package umc.reviewinclass.service.LectureService;

import umc.reviewinclass.web.dto.Lecture.LectureResponseDTO;

public interface LectureQueryService {
    // 강의 상세 조회
    LectureResponseDTO.LectureDTO getLectureInfo(Long lectureId);
}
