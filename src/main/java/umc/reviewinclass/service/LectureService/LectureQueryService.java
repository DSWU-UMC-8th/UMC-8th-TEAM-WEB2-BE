package umc.reviewinclass.service.LectureService;

import umc.reviewinclass.web.dto.lecture_tmp.LectureResponseDTO;
import umc.reviewinclass.web.dto.lecture_tmp.LectureSearchResponseDTO;

import java.util.List;

public interface LectureQueryService {
    // 강의 상세 조회
    LectureResponseDTO.LectureDTO getLectureInfo(Long lectureId);

    // 강의 검색
    List<LectureSearchResponseDTO.LectureDTO> search(String query);
}
