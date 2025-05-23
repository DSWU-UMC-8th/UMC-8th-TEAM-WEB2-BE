package umc.reviewinclass.service.ReviewService;

import umc.reviewinclass.web.dto.review.ReviewCreateRequestDTO_;

public interface ReviewCommandService {
    Long createReview(ReviewCreateRequestDTO_ requestDto);
}
