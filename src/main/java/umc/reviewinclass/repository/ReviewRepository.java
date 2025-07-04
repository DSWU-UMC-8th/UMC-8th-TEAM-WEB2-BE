package umc.reviewinclass.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import umc.reviewinclass.domain.lecture.Lecture;
import umc.reviewinclass.domain.review.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom, JpaSpecificationExecutor<Review> {
    List<Review> findAllByLecture(Lecture lecture);

    Page<Review> findAll(Pageable pageable);
}
