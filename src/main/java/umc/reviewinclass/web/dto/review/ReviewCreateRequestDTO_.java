package umc.reviewinclass.web.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCreateRequestDTO_ {

    private Long lectureId;       // 강의명
    private List<Long> platformIds;      // 플랫폼명
    private String imageUrl;          // 이미지 URL
    private Float rating;            // 평점
    private String content;           // 강의평
    private String studyPeriod;       // studyPeriod
}

