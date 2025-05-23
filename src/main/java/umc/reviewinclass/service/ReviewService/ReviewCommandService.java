package umc.reviewinclass.service.ReviewService;

import org.springframework.web.multipart.MultipartFile;
import umc.reviewinclass.web.dto.review.ReviewCreateRequestDTO;

public interface ReviewCommandService {
    Long createReview(ReviewCreateRequestDTO requestDto, MultipartFile image);

    void likeReview(Long reviewId);
}
