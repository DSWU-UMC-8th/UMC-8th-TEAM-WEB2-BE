package umc.reviewinclass.web.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.reviewinclass.web.dto.lecture.LectureRequestDTO;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCreateRequestDTO {

    private Long lectureId;       // 강의명
    private LectureRequestDTO.createLectureDTO lecture;      // 만약, 원하는 강의 없으면 강의 등록까지 할 수 있도록
    private List<Long> platformIds;      // 플랫폼명
    private Float rating;            // 평점
    private String content;           // 강의평
    private String studyPeriod;       // studyPeriod
}

