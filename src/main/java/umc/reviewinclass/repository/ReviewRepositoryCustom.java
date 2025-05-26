package umc.reviewinclass.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.reviewinclass.web.dto.review.ReviewSearchListResponseDTO;

public interface ReviewRepositoryCustom {

    // 리뷰 검색
    Page<ReviewSearchListResponseDTO.ReviewDTO> searchReviews(
            String query, String sort, Double minRating, Double maxRating, Pageable pageable
    );
}