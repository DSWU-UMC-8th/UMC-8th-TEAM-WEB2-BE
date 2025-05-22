package umc.reviewinclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.reviewinclass.domain.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
