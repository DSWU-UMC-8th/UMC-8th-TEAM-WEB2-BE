package umc.reviewinclass.web.dto.review;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PopularReviewResponseDTO {

    private Long reviewId;
    private String lectureName;
    private String instructorName;
    private Double rating;
    private String content;
    private Long likes;

}

