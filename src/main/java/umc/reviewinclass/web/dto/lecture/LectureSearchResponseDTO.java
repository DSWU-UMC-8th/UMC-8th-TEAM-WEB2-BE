package umc.reviewinclass.web.dto.lecture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureSearchResponseDTO {
    private List<LectureDTO> lectures;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LectureDTO {
        private Long id;
        private String name;
        private String instructor;
        private String platform;
    }
}