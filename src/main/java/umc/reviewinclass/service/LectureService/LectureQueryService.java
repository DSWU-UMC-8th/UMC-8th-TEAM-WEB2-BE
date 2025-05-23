package umc.reviewinclass.service.LectureService;

import umc.reviewinclass.web.dto.lecture.LectureRatingSummaryDto;
import umc.reviewinclass.web.dto.lecture.LectureResponseDTO;
import umc.reviewinclass.web.dto.lecture.LectureSearchResponseDTO;

import java.util.List;
import java.util.Map;

public interface LectureQueryService {
    // 강의 상세 조회
    LectureResponseDTO.LectureDTO getLectureInfo(Long lectureId);

    // 강의 검색
    List<LectureSearchResponseDTO.LectureDTO> search(String query);

    //강의별 평점 & 별점 개수 조회
    LectureRatingSummaryDto getLectureRatingSummary(Long lectureId);

    // 전체 강의 목록 조회
    List<Map<String, Object>> getLectures();

}
