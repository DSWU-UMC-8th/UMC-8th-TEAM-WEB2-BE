package umc.reviewinclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.reviewinclass.domain.review.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
