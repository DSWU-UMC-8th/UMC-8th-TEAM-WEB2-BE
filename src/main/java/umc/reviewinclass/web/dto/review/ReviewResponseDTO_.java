package umc.reviewinclass.web.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDTO_ {

    private Long reviewId;
    private String content;
    private Double rating;
    private String period;
    private Long likes;
    private String createdAt;
}

