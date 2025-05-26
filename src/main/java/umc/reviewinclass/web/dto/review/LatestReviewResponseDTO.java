package umc.reviewinclass.web.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LatestReviewResponseDTO {

    private Long reviewId;
    private Double rating;
    private String studyPeriod;
    private Long likes;
    private String content;
    private String createdAt;

}