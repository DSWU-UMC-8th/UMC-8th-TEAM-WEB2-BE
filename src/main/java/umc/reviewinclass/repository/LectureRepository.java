package umc.reviewinclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.reviewinclass.domain.lecture.Lecture;

import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

    Optional<Lecture> findByNameContaining(String name);
}

