package umc.reviewinclass.service.ReviewService;

import umc.reviewinclass.domain.enums.CategoryType;
import umc.reviewinclass.domain.enums.Level;
import umc.reviewinclass.domain.enums.StudyPeriod;
import umc.reviewinclass.web.dto.review.LatestReviewResponseDTO;
import umc.reviewinclass.web.dto.review.PopularReviewResponseDTO;

import java.util.List;

public interface ReviewQueryService {
    List<PopularReviewResponseDTO> getPopularReviews();
    List<LatestReviewResponseDTO> getLatestReviews();
    List<PopularReviewResponseDTO> getFilteredReviews(CategoryType category, Level level, StudyPeriod period);
}
