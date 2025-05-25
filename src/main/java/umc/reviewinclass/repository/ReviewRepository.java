package umc.reviewinclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umc.reviewinclass.domain.lecture.Lecture;
import umc.reviewinclass.domain.review.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
    List<Review> findAllByLecture(Lecture lecture);

    @Query("SELECT r FROM Review r JOIN FETCH r.lecture l ORDER BY r.likes DESC")
    List<Review> findTopReviews(); // 인기 리뷰

    @Query("SELECT r FROM Review r JOIN FETCH r.lecture l ORDER BY r.createdAt DESC")
    List<Review> findRecentReviews(); // 최신 리뷰

}
