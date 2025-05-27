package umc.reviewinclass.web.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewSearchListResponseDTO {

    private Integer page;
    private Integer size;
    private Long totalCount;
    private Boolean hasNext;
    private List<ReviewDTO> reviews;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ReviewDTO {
        private Long id;
        private Long lectureId;
        private String content;
        private Float rating;
        private String period;
        private Long likes;
        private String createdAt;
    }
}