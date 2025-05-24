package umc.reviewinclass.repository;

import umc.reviewinclass.domain.enums.CategoryType;
import umc.reviewinclass.domain.enums.Level;
import umc.reviewinclass.domain.enums.StudyPeriod;
import umc.reviewinclass.domain.review.Review;

import java.util.List;

public interface ReviewRepositoryCustom {
    List<Review> filterReviews(CategoryType category, Level level, StudyPeriod period);
}
