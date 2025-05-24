package umc.reviewinclass.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.reviewinclass.apiPayload.code.status.ErrorStatus;
import umc.reviewinclass.apiPayload.exception.handler.ReviewHandler;
import umc.reviewinclass.converter.ReviewConverter;
import umc.reviewinclass.domain.enums.CategoryType;
import umc.reviewinclass.domain.enums.Level;
import umc.reviewinclass.domain.enums.StudyPeriod;
import umc.reviewinclass.domain.review.Review;
import umc.reviewinclass.repository.ReviewRepository;
import umc.reviewinclass.web.dto.review.LatestReviewResponseDTO;
import umc.reviewinclass.web.dto.review.PopularReviewResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<PopularReviewResponseDTO> getPopularReviews() {
        List<Review> reviews = reviewRepository.findTopReviews();
        if (reviews.isEmpty()) {
            throw new ReviewHandler(ErrorStatus.REVIEW_NOT_FOUND);
        }
        return ReviewConverter.toPopularDTOList(reviews);
    }

    @Override
    public List<LatestReviewResponseDTO> getLatestReviews() {
        List<Review> reviews = reviewRepository.findRecentReviews();
        if (reviews.isEmpty()) {
            throw new ReviewHandler(ErrorStatus.REVIEW_NOT_FOUND);
        }
        return ReviewConverter.toLatestDTOList(reviews);
    }

    @Override
    public List<PopularReviewResponseDTO> getFilteredReviews(CategoryType category, Level level, StudyPeriod period) {
        List<Review> reviews = reviewRepository.filterReviews(category, level, period);
        if (reviews.isEmpty()) {
            throw new ReviewHandler(ErrorStatus.REVIEW_NOT_FOUND);
        }
        return ReviewConverter.toPopularDTOList(reviews);
    }

}
