package umc.reviewinclass.service.ReviewService;

import umc.reviewinclass.web.dto.review.LatestReviewResponseDTO;
import umc.reviewinclass.web.dto.review.PopularReviewResponseDTO;
import umc.reviewinclass.web.dto.review.ReviewResponseDTO;

import java.util.List;

public interface ReviewQueryService {
    List<PopularReviewResponseDTO> getPopularReviews();
    List<LatestReviewResponseDTO> getLatestReviews();
}
