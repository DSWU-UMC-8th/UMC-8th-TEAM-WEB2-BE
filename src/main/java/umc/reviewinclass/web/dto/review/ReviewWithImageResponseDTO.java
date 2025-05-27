package umc.reviewinclass.web.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewWithImageResponseDTO {

    private Long reviewId;
    private String content;
    private Double rating;
    private String period;
    private Long likes;
    private String imageUrl;
    private String createdAt;
}