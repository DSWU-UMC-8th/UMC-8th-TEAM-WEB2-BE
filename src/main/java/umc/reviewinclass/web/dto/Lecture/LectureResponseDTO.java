package umc.reviewinclass.web.dto.Lecture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.reviewinclass.domain.enums.CategoryType;
import umc.reviewinclass.domain.enums.Level;

import java.util.List;

public class LectureResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LectureDTO {
        // Long id;
        String name;            // 강의명
        String instructorName;  // 강사명
        String platformName;      // 플랫폼 이름
        Level level;            // 난이도
        CategoryType category;      // 카테고리

        List<String> imgUrls;   // 강의 썸네일 url 목록
    }
}
