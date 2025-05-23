package umc.reviewinclass.service.ReviewService;

import umc.reviewinclass.web.dto.review.ReviewCreateRequestDTO;

public interface ReviewCommandService {
    Long createReview(ReviewCreateRequestDTO requestDto);
}
