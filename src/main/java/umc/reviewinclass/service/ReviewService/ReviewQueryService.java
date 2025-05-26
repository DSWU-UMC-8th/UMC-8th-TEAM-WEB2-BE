package umc.reviewinclass.service.ReviewService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.reviewinclass.domain.enums.CategoryType;
import umc.reviewinclass.domain.enums.Level;
import umc.reviewinclass.domain.enums.StudyPeriod;
import umc.reviewinclass.web.dto.review.LatestReviewResponseDTO;
import umc.reviewinclass.web.dto.review.PopularReviewResponseDTO;
import umc.reviewinclass.web.dto.review.ReviewListDTO;

import java.util.List;

public interface ReviewQueryService {
    Page<PopularReviewResponseDTO> getPopularReviews(Pageable pageable);
    Page<LatestReviewResponseDTO> getLatestReviews(Pageable pageable);
    List<PopularReviewResponseDTO> getFilteredReviews(CategoryType category, Level level, StudyPeriod period);
    ReviewListDTO getLectureReviews(Long lectureId, Double rating, String sortField, int page);
}
