package umc.reviewinclass.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.reviewinclass.domain.enums.Category;
import umc.reviewinclass.domain.enums.Level;
import umc.reviewinclass.domain.enums.Platform;

import java.util.List;

public class LectureRequestDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class createLectureDTO{
        private String name;            // 강의명
        private String instructorName;  // 강사명
        private Platform platform;      // 플랫폼
        private Level level;            // 난이도
        private Category category;      // 카테고리

        private List<String> imgUrls;   // 강의 썸네일 url 목록
    }
}
