package umc.reviewinclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.reviewinclass.domain.lecture.Lecture;
import umc.reviewinclass.domain.review.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByLecture(Lecture lecture);
}
