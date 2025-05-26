package umc.reviewinclass.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.reviewinclass.repository.ReviewRepository;
import umc.reviewinclass.web.dto.review.ReviewSearchListResponseDTO;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Override
    public Page<ReviewSearchListResponseDTO.ReviewDTO> searchReviews(
            String query, String sort, Double minRating, Double maxRating, Pageable pageable
    ) {
        return reviewRepository.searchReviews(query, sort, minRating, maxRating, pageable);
    }
}
