package umc.reviewinclass.service.ReviewService;

import umc.reviewinclass.web.dto.review.ReviewCreateRequestDto;

public interface ReviewCommandService {
    Long createReview(ReviewCreateRequestDto requestDto);
}
