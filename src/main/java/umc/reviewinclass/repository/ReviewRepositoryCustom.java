package umc.reviewinclass.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.reviewinclass.web.dto.review.ReviewSearchListResponseDTO;
import umc.reviewinclass.domain.enums.CategoryType;
import umc.reviewinclass.domain.enums.Level;
import umc.reviewinclass.domain.enums.StudyPeriod;
import umc.reviewinclass.domain.review.Review;

import java.util.List;

public interface ReviewRepositoryCustom {

    List<Review> filterReviews(CategoryType category, Level level, StudyPeriod period);

    // 리뷰 검색
    Page<ReviewSearchListResponseDTO.ReviewDTO> searchReviews(
            String query, String sort, Double minRating, Double maxRating, Pageable pageable
    );
}