package umc.reviewinclass.web.dto.lecture;

import lombok.*;
import umc.reviewinclass.domain.enums.CategoryType;
import umc.reviewinclass.domain.enums.Level;

import java.util.List;

public class LectureRequestDTO {

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class createLectureDTO{
        private String name;            // 강의명
        private String instructorName;  // 강사명
        private Long platformId;      // 플랫폼 id
        private Level level;            // 난이도
        private CategoryType category;      // 카테고리

        // private List<String> imgUrls;   // 강의 썸네일 url 목록
    }
}
